package com.zsdk.server.util;

import com.zsdk.server.client.LoginResult;
import com.zsdk.server.client.Result;
import com.zsdk.server.model.UserInfo;

/**
 * Created by zhj on 17/3/17.
 */
public class LoginResultBuilder {

    public final static int TOKEN_ERROR = -6;
    public final static int MULTI_ERROR = -5;
    public final static int DOUBLE_USER = -4;
    public final static int PARAMS_ERROR = -3;
    public final static int PASSWORD_ERROR = -2;
    public final static int NO_USER = -1;
    public final static int SUCCESS = 0;

    public static Result<LoginResult> sucess(LoginResult userInfo) {
        Result<LoginResult> loginResult = new Result<LoginResult>();
        loginResult.setCode(SUCCESS);
        loginResult.setData(userInfo);
        loginResult.setMsg("成功");
        return loginResult;
    }


    public static <T> Result<T> doubleUser() {
        return new Result<T>(DOUBLE_USER,"用户已存在");
    }
    public static <T> Result<T> noUser() {
        return new Result<T>(NO_USER,"用户不存在");
    }
    public static <T> Result<T> passwordError() { return new Result<T>(PASSWORD_ERROR,"密码错误");}
    public static <T> Result<T> paramsError() {
        return new Result<T>(PARAMS_ERROR,"参数错误");
    }
    public static <T> Result<T> multiError() {
        return new Result<T>(MULTI_ERROR,"重复登录");
    }

    public static <T> Result<T> tokenError() {
        return new Result<T>(TOKEN_ERROR,"验证登录");
    }
}
