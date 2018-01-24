package com.zsdk.server.controller;


import com.zsdk.server.bean.Result;
import com.zsdk.server.cache.CacheManager;
import com.zsdk.server.client.LoginInfo;
import com.zsdk.server.client.LoginResult;
import com.zsdk.server.client.TokenCheck;
import com.zsdk.server.config.Configuration;
import com.zsdk.server.model.GameInfo;
import com.zsdk.server.model.UserInfo;
import com.zsdk.server.service.LoginService;
import com.zsdk.server.util.*;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
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


    private boolean checkSign(LoginInfo loginInfo) {
        JSONObject json = JSONObject.fromObject(loginInfo);
        List<String> keys = new ArrayList<String>(json.keySet());
        keys.remove("sign");
        String content = StringUtil.getSignData(json, keys, "&", "=") + "&" + Configuration.CLIENT_SIGN_KEY;
        return EncryptUtil.md5(content).equals(loginInfo.getSign());
    }

    private com.zsdk.server.client.Result<LoginResult> doLogin(UserInfo userInfo, String appId) {

        GameInfo gameInfo = CacheManager.getInstance().getGame(Integer.parseInt(appId));
        String token = EncryptUtil.genToken(userInfo, gameInfo.getAppKey());
        boolean flag = redisUtil.setToken(Configuration.CLIENT_LOGIN_TOKEN_PREFIX + token, "" + userInfo.getUid(), Configuration.CLIENT_LOGIN_TOKEN_LAST_TIME);

        if (!flag) {
            return LoginResultBuilder.<LoginResult>multiError();
        }

        LoginResult loginResult = new LoginResult();
        loginResult.setUid("" + userInfo.getUid());
        loginResult.setUsername(userInfo.getUserName());
        loginResult.setPassword(userInfo.getPassword());
        loginResult.setToken(token);
        return LoginResultBuilder.sucess(loginResult);
    }


    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public com.zsdk.server.client.Result<LoginResult> login(@RequestBody LoginInfo loginInfo) {
        if (ObjectUtil.isPropertyNull(loginInfo)) {
            return LoginResultBuilder.<LoginResult>paramsError();
        }
        if (!checkSign(loginInfo)) {
            return LoginResultBuilder.<LoginResult>paramsError();
        }
        UserInfo userInfo = loginService.selectByName(loginInfo.getUsername());
        if (userInfo == null) {
            return LoginResultBuilder.<LoginResult>noUser();
        } else if (userInfo.getPassword().equals(loginInfo.getPassword())) {
            return doLogin(userInfo, loginInfo.getAppId());
        } else {
            return LoginResultBuilder.<LoginResult>passwordError();

        }
    }

    @RequestMapping("/register.do")
    @ResponseBody
    public com.zsdk.server.client.Result<LoginResult> register(HttpServletRequest request, @RequestBody LoginInfo loginInfo) {
        String ip = HttpUtil.getIpAddress(request);
        if (ObjectUtil.isPropertyNull(loginInfo)) {
            return LoginResultBuilder.<LoginResult>paramsError();
        }
        if (!checkSign(loginInfo)) {
            return LoginResultBuilder.<LoginResult>paramsError();
        }
        String place = "unknown";

        try {
            String url = Configuration.TAO_BAO_IP_PLACE + ip;
            String result = HttpUtil.get(url);
            JSONObject json = JSONObject.fromObject(result);
            if (json.containsKey("code") && json.getInt("code") == 0) {
                JSONObject data = json.getJSONObject("data");
                place = data.getString("country") + data.getString("region") + data.getString("city");
            }
        } catch (Exception e) {
            Log.e("register error", e);
            e.printStackTrace();
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
        userInfo.setRegisterPlace(place);
        try {
            loginService.insert(userInfo);
        } catch (Exception e) {
            success = false;
        }
        if (success) {
            return doLogin(userInfo, loginInfo.getAppId());
        }
        return LoginResultBuilder.<LoginResult>doubleUser();
    }


//    @Autowired
//    private GameInfoMapper gameInfoMapper;

    @RequestMapping("/test.do")
    @ResponseBody
    public void test(HttpServletRequest request, HttpServletResponse response) {
//        List<GameInfo> all = gameInfoMapper.findAll();
//        int i = 1;
    }

    //    todo
//这个方法 如果能接受application/x-www-form-urlencoded 和 application/json
//    但是application/json 对象不能注入tokenCheck,即使设置了 produces = MediaType.APPLICATION_JSON_UTF8_VALUE
//  如果加le@RequestBody,只能接收json 对象，也能注入tokenCheck
//    .......
    @RequestMapping(path = "/check.do", method = RequestMethod.POST)
    @ResponseBody
    public Result check(TokenCheck tokenCheck) {
        Result result = new Result();
        if (ObjectUtil.isPropertyNull(tokenCheck)) {
            return result;
        }
        String token = tokenCheck.getToken();
        String key = Configuration.CLIENT_LOGIN_TOKEN_PREFIX + token;
        String uid = redisUtil.get(key);
        if (uid == null) {
            result.setMsg("token不存在");
            result.setState(-2);
            return result;
        }
        GameInfo gameInfo = CacheManager.getInstance().getGame(Integer.parseInt(tokenCheck.getAppId()));
        if (gameInfo == null) {
            result.setMsg("游戏不存在");
            result.setState(-3);
            return result;
        }
        String content = tokenCheck.getAppId() + gameInfo.getAppKey() + token;
        String sign = EncryptUtil.md5(content);
        if (sign.equals(tokenCheck.getSign())) {
            HttpUtil.optionSuccess(result);
            redisUtil.del(key);
        }
        return result;
    }

}
