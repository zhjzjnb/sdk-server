package com.zsdk.server.dao;

import com.zsdk.server.model.AdminInfo;

public interface AdminInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminInfo record);

    AdminInfo selectByName(String userName);

}