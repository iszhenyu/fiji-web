package tech.jianshuo.fiji.security.service.impl;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.jianshuo.fiji.biz.helper.PrincipalHelper;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.biz.service.UserService;
import tech.jianshuo.fiji.common.util.RandomUtils;
import tech.jianshuo.fiji.common.util.TimeUtils;
import tech.jianshuo.fiji.security.RetryLimitHashedCredentialsMatcher;
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
    private RetryLimitHashedCredentialsMatcher credentialsMatcher;

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
        String salt = RandomUtils.randomNumeric(4);
        builder.setSalt(salt);
        return userService.addUser(builder.build());
    }

    private class UserBuilder {
        private String username;
        private String mobile;
        private String email;
        private String password;
        private String salt;

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

        void setSalt(String salt) {
            this.salt = salt;
        }

        User build() {
            User user = new User();
            user.setUsername(username);
            user.setMobile(mobile);
            user.setEmail(email);
            user.setSalt(salt);
            user.setPassword(genPasswordHash());
            user.setDeletedAt(0L);
            user.setCreateTime(TimeUtils.currentTime());
            user.setLastModifyTime(TimeUtils.currentTime());
            return user;
        }

        String genPasswordHash() {
            SimpleHash hash = new SimpleHash(credentialsMatcher.getHashAlgorithmName(), password, salt, credentialsMatcher.getHashIterations());
            return hash.toHex();
        }
    }
}
