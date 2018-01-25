package com.zsdk.server.dao;

import com.zsdk.server.model.AdminInfo;

import java.util.List;

public interface AdminInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminInfo record);

    AdminInfo selectByName(String userName);

    List<AdminInfo> getAll();

}