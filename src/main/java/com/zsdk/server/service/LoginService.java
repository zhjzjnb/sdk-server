package com.zsdk.server.service;

import com.zsdk.server.model.UserInfo;

import java.util.Map;

/**
 * Created by zhj on 17/3/16.
 */
public interface LoginService {
    UserInfo selectByName(String userName);

    UserInfo insert(UserInfo info);

}
