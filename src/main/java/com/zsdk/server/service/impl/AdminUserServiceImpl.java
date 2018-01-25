package com.zsdk.server.service.impl;

import com.zsdk.server.dao.AdminInfoMapper;
import com.zsdk.server.model.AdminInfo;
import com.zsdk.server.model.AdminUser;
import com.zsdk.server.service.AdminUserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhj on 17/3/17.
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    AdminInfoMapper adminInfoMapper;

    @Override
    public boolean isValid(AdminInfo adminUser) {
        AdminInfo exits = adminInfoMapper.selectByName(adminUser.getUserName());
        return exits == null ? false : exits.getPassword().equals(adminUser.getPassword());
    }

    @Override
    public List<AdminInfo> getAll() {
        return adminInfoMapper.getAll();
    }
}
