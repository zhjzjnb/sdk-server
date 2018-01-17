package com.zsdk.server.service.impl;

import com.zsdk.server.dao.UserInfoMapper;
import com.zsdk.server.model.AdminUser;
import com.zsdk.server.model.UserInfo;
import com.zsdk.server.service.AdminUserService;
import com.zsdk.server.service.LoginService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by zhj on 17/3/17.
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {


    @Override
    public boolean isValid(AdminUser adminUser) {
        return true;
    }
}
