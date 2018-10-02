package tech.jianshuo.fiji.admin.service;

import tech.jianshuo.fiji.biz.model.admin.AdminUser;

import java.io.Serializable;

public interface AdminPassportService {

    default AdminUser loginWithRememberMe(String principal, String credential) {
        return login(principal, credential, true);
    }

    AdminUser login(String principal, String credential, boolean rememberMe);

    Serializable loadLoginedToken();

    /**
     * 更新用户最后一次登录的状态信息
     */
    AdminUser updateUserLastLoginInfo(AdminUser user);

    AdminUser registerUser(String principal, String credential);

    void logoutUser();
}
