package com.xiaomianshi.service.impl;

import com.xiaomianshi.entity.user.User;
import com.xiaomianshi.form.RegisterForm;
import com.xiaomianshi.helper.PasswordHelper;
import com.xiaomianshi.repository.UserRepository;
import com.xiaomianshi.service.UserService;
import com.xiaomianshi.util.RandomUtils;
import com.xiaomianshi.helper.PrincipalHelper;
import com.xiaomianshi.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhen.yu
 * @since 2018/7/10
 */
@Service
public class UserServiceImpl extends FijiService implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUser(String principal) {
        if (PrincipalHelper.isMobile(principal)) {
            return userRepository.findByMobile(principal);
        } else if (PrincipalHelper.isEmail(principal)) {
            return userRepository.findByEmail(principal);
        }
        return userRepository.findByUsername(principal);
    }

    public User registerUser(RegisterForm form) {
        UserBuilder builder = UserBuilder.create();
        builder.setUsername(form.getUsername());
        builder.setPassword(form.getPassword());
        String salt = RandomUtils.randomNumeric(4);
        builder.setSalt(salt);
        return userRepository.save(builder.build());
    }

    private static class UserBuilder {
        private String username;
        private String mobile;
        private String email;
        private String password;
        private String salt;

        static UserBuilder create() {
            return new UserBuilder();
        }

        UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        UserBuilder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        UserBuilder setSalt(String salt) {
            this.salt = salt;
            return this;
        }

        User build() {
            User user = new User();
            user.setUsername(username);
            user.setMobile(mobile);
            user.setEmail(email);
            String passwordHash = PasswordHelper.encrypt(password, salt);
            user.setPassword(passwordHash);
            user.setSalt(salt);
            user.setCreateTime(TimeUtils.currentTime());
            user.setLastModifyTime(TimeUtils.currentTime());
            return user;
        }
    }

}
