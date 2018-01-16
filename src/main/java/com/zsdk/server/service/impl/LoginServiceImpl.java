package com.zsdk.server.service.impl;

import com.zsdk.server.dao.UserInfoMapper;
import com.zsdk.server.model.UserInfo;
import com.zsdk.server.service.LoginService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by zhj on 17/3/17.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo selectByName(String userName) {
       return userInfoMapper.selectByName(userName);
    }

    @Override
    public UserInfo insert(UserInfo info) {
        userInfoMapper.insert(info);
        return info;
    }
}
