package com.zsdk.server.service;

import com.zsdk.server.model.AdminInfo;
import com.zsdk.server.model.AdminUser;

import java.util.List;

public interface AdminUserService {
    boolean isValid(AdminUser adminUser);

    List<AdminInfo> getAll();
}
