package com.zsdk.server.service;

import com.zsdk.server.model.AdminInfo;
import com.zsdk.server.model.AdminUser;

import java.util.List;

public interface AdminUserService {
    boolean isValid(AdminInfo adminInfo);

    List<AdminInfo> getAll();
}
