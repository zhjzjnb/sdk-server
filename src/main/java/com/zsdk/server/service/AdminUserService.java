package com.zsdk.server.service;

import com.zsdk.server.model.AdminUser;
import com.zsdk.server.model.UserInfo;

import java.util.Map;

public interface AdminUserService {
    boolean isValid(AdminUser adminUser);

}
