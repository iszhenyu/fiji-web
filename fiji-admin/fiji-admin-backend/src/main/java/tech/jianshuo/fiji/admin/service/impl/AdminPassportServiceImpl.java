package tech.jianshuo.fiji.admin.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.jianshuo.fiji.admin.service.AdminPassportService;
import tech.jianshuo.fiji.admin.service.AdminUserService;
import tech.jianshuo.fiji.biz.model.admin.AdminUser;
import tech.jianshuo.fiji.core.exception.ValidationException;
import tech.jianshuo.fiji.security.service.PasswordService;

import java.io.Serializable;

/**
 * @author zhenyu
 * Created on 2018-10-09
 */
@Slf4j
@Service
public class AdminPassportServiceImpl implements AdminPassportService {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private PasswordService passwordService;

    @Override
    public AdminUser login(String principal, String credential, boolean rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            throw new ValidationException("已经登录，不要重复登录");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(principal, credential);
        token.setRememberMe(rememberMe);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            throw new ValidationException("用户名或密码错误");
        }
        AdminUser user = adminUserService.loadAdminUserByPrincipal(principal);
        return updateUserLastLoginInfo(user);
    }

    @Override
    public AdminUser updateUserLastLoginInfo(AdminUser user) {
        if (user != null) {
            // TODO
        }
        return user;
    }

    @Override
    public Serializable loadLoginedToken() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        if (session == null) {
            return null;
        }
        return session.getId();
    }

    @Override
    public void logoutUser() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            throw new ValidationException("已经退出登录");
        }
        subject.logout();
    }
}
