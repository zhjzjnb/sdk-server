package com.zsdk.server.dao;

import com.zsdk.server.model.UserInfo;
import com.zsdk.server.model.UserInfoKey;

public interface UserInfoMapper {
    UserInfo selectByName(String userName);

    int insert(UserInfo record);
}