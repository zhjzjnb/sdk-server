package com.zsdk.server.controller;


import com.google.gson.Gson;
import com.zsdk.server.cache.CacheManager;
import com.zsdk.server.client.LoginInfo;
import com.zsdk.server.client.LoginResult;
import com.zsdk.server.client.Result;
import com.zsdk.server.config.Configuration;
import com.zsdk.server.dao.GameInfoMapper;
import com.zsdk.server.model.GameInfo;
import com.zsdk.server.model.UserInfo;
import com.zsdk.server.service.LoginService;
import com.zsdk.server.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * Created by zhj on 2017/3/14.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisUtil redisUtil;


    private void doLogin(HttpServletResponse response, UserInfo userInfo, String appId) throws IOException {

        GameInfo gameInfo = CacheManager.getInstance().getGame(Integer.parseInt(appId));
        String token = EncryptUtil.genToken(userInfo, gameInfo.getAppKey());
        boolean flag = redisUtil.setToken(Configuration.CLIENT_LOGIN_TOKEN_PREFIX + token, "" + userInfo.getUid(), Configuration.CLIENT_LOGIN_TOKEN_LAST_TIME);

//        Log.i("token ok:"+String.valueOf(flag));
        if (!flag){
            HttpUtil.replyToClient(response, LoginResultBuilder.<LoginResult>multiError());
            return;
        }

        LoginResult loginResult = new LoginResult();
        loginResult.setUid("" + userInfo.getUid());
        loginResult.setUsername(userInfo.getUserName());
        loginResult.setPassword(userInfo.getPassword());
        loginResult.setToken(token);

        HttpUtil.replyToClient(response, LoginResultBuilder.sucess(loginResult));
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public void login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginInfo loginInfo) throws IOException {

        if (ObjectUtil.isPropertyNull(loginInfo)) {
            HttpUtil.replyToClient(response, LoginResultBuilder.<LoginResult>paramsError());
            return;
        }
        UserInfo userInfo = loginService.selectByName(loginInfo.getUsername());
        if (userInfo == null) {
            HttpUtil.replyToClient(response, LoginResultBuilder.<LoginResult>noUser());
        } else if (userInfo.getPassword().equals(loginInfo.getPassword())) {
            doLogin(response, userInfo, loginInfo.getAppId());
        } else {
            HttpUtil.replyToClient(response, LoginResultBuilder.<LoginResult>passwordError());
        }
    }

    @RequestMapping("/register.do")
    @ResponseBody
    public void register(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginInfo loginInfo) throws IOException {
        String ip = HttpUtil.getIpAddress(request);
        if (ObjectUtil.isPropertyNull(loginInfo)) {
            HttpUtil.replyToClient(response, LoginResultBuilder.<LoginResult>paramsError());
            return;
        }

        boolean success = true;
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(loginInfo.getUsername());
        userInfo.setPassword(loginInfo.getPassword());
        userInfo.setPlatform(loginInfo.getPlatform());
        userInfo.setRoot(Short.parseShort(loginInfo.getRoot()));
        userInfo.setSystemVersion(loginInfo.getSystemVersion());
        userInfo.setSystemName(loginInfo.getSystemName());
        userInfo.setDeviceModel(loginInfo.getDeviceModel());
        userInfo.setDeviceName(loginInfo.getDeviceName());
        userInfo.setRegisterTime(new Date());
        userInfo.setRegisterPlace("todo");
        try {
            loginService.insert(userInfo);
        } catch (Exception e) {
            success = false;
            HttpUtil.replyToClient(response, LoginResultBuilder.<LoginResult>doubleUser());
        }
        if (success) {
            doLogin(response, userInfo, loginInfo.getAppId());
        }
    }


//    @Autowired
//    private GameInfoMapper gameInfoMapper;

    @RequestMapping("/test.do")
    @ResponseBody
    public void test(HttpServletRequest request, HttpServletResponse response) {
//        List<GameInfo> all = gameInfoMapper.findAll();
//        int i = 1;
    }


}
