package tech.jianshuo.fiji.security.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.jianshuo.fiji.biz.helper.PrincipalHelper;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.biz.service.UserService;
import tech.jianshuo.fiji.common.util.TimeUtils;
import tech.jianshuo.fiji.core.exception.ValidationException;
import tech.jianshuo.fiji.security.service.PasswordService;
import tech.jianshuo.fiji.security.service.PassportService;

/**
 * @author zhen.yu
 * Created on 2018-07-26
 */
@Service
public class PassportServiceImpl implements PassportService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    @Override
    public User login(String principal, String credential, boolean rememberMe) {
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
        User user = userService.loadUserByPrincipal(principal);
        return updateUserLastLoginInfo(user);
    }

    @Override
    public User updateUserLastLoginInfo(User user) {
        if (user != null) {
            // TODO
        }
        return user;
    }

    @Override
    public User registerUser(String principal, String credential) {
        UserBuilder builder = new UserBuilder();
        if (PrincipalHelper.isMobile(principal)) {
            builder.setMobile(principal);
            builder.setUsername(principal + "@");
        } else if (PrincipalHelper.isEmail(principal)) {
            builder.setEmail(principal);
            builder.setUsername(principal.substring(0, principal.indexOf("@") + 1));
        } else {
            builder.setUsername(principal);
        }
        builder.setPassword(credential);
        return userService.createUser(builder.build());
    }

    private class UserBuilder {
        private String username;
        private String mobile;
        private String email;
        private String password;

        void setUsername(String username) {
            this.username = username;
        }

        void setMobile(String mobile) {
            this.mobile = mobile;
        }

        void setEmail(String email) {
            this.email = email;
        }

        void setPassword(String password) {
            this.password = password;
        }

        User build() {
            User user = new User();
            user.setUsername(username);
            user.setMobile(mobile);
            user.setEmail(email);
            String salt = passwordService.generateSalt();
            user.setSalt(salt);
            String encryptedPassword = passwordService.encryptPassword(password, salt);
            user.setPassword(encryptedPassword);
            user.setDeletedAt(0L);
            user.setCreateTime(TimeUtils.currentTime());
            user.setLastModifyTime(TimeUtils.currentTime());
            return user;
        }
    }

    @Override
    public void logoutUser() {
        SecurityUtils.getSubject().logout();
    }
}
