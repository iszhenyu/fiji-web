package tech.jianshuo.fiji.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.jianshuo.fiji.biz.helper.PrincipalHelper;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.biz.service.UserService;
import tech.jianshuo.fiji.common.util.TimeUtils;
import tech.jianshuo.fiji.security.service.PasswordService;
import tech.jianshuo.fiji.security.service.SecurityService;

/**
 * @author zhen.yu
 * Created on 2018-07-26
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

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
        return userService.addUser(builder.build());
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
}
