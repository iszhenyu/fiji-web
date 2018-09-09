package tech.jianshuo.fiji.security.service;

import tech.jianshuo.fiji.biz.model.user.User;

/**
 * @author zhen.yu
 * Created on 2018-07-26
 */
public interface PassportService {

    default User loginWithRememberMe(String principal, String credential) {
        return login(principal, credential, true);
    }

    User login(String principal, String credential, boolean rememberMe);

    /**
     * 更新用户最后一次登录的状态信息
     */
    User updateUserLastLoginInfo(User user);

    User registerUser(String principal, String credential);

    void logoutUser();
}
