package com.zsdk.server.controller;


import com.google.gson.Gson;
import com.zsdk.server.client.LoginInfo;
import com.zsdk.server.client.LoginResult;
import com.zsdk.server.client.Result;
import com.zsdk.server.model.UserInfo;
import com.zsdk.server.service.LoginService;
import com.zsdk.server.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

/**
 * Created by zhj on 2017/3/14.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;


    private void doLogin(HttpServletResponse response, UserInfo userInfo, String appId) throws IOException {

        LoginResult loginResult = new LoginResult();
        loginResult.setUid("" + userInfo.getUid());
        loginResult.setUsername(userInfo.getUserName());
        loginResult.setPassword(userInfo.getPassword());
        loginResult.setToken(EncryptUtil.genToken(appId));

        HttpUtil.replyToClient(response, LoginResultBuilder.sucess(loginResult));
    }

    private LoginInfo checkParam(HttpServletRequest request, HttpServletResponse response) {
        String str = HttpUtil.getHttpPostBodyString(request);
        try {
            str = URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("error url decode", e);
        }

        Result<LoginResult> result = null;

        Gson json = new Gson();
        LoginInfo loginInfo = json.fromJson(str, LoginInfo.class);
        Log.i("[LoginController login]: " + str);

        if (ObjectUtil.isPropertyNull(loginInfo)) {
            result = LoginResultBuilder.paramsError();
            HttpUtil.replyToClient(response, result);
            return null;
        }

        return loginInfo;
    }

    @RequestMapping("/login")
    @ResponseBody
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        LoginInfo loginInfo = checkParam(request, response);
        if (loginInfo == null) {
            return;
        }
        UserInfo userInfo = loginService.selectByName(loginInfo.getUsername());

        if (userInfo.getPassword().equals(loginInfo.getPassword())) {
            doLogin(response, userInfo, loginInfo.getAppId());
            return;
        } else {
            HttpUtil.replyToClient(response, LoginResultBuilder.passwordError());
        }
    }

    @RequestMapping("/register")
    @ResponseBody
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ip = HttpUtil.getIpAddress(request);
        LoginInfo loginInfo = checkParam(request, response);
        if (loginInfo == null) {
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
            HttpUtil.replyToClient(response, LoginResultBuilder.doubleUser());
        }
        if (success) {
            doLogin(response, userInfo, loginInfo.getAppId());
        }
    }


    @RequestMapping("/test")
    @ResponseBody
    public void test(HttpServletRequest request, HttpServletResponse response) {
        UserInfo userInfo = loginService.selectByName("123");
        if (userInfo == null) {
            Log.i("null");
        } else {
            Log.i("not null");
        }
    }


}
