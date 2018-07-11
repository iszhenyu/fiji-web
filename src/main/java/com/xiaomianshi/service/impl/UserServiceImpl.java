package com.xiaomianshi.service.impl;

import com.xiaomianshi.entity.user.User;
import com.xiaomianshi.form.RegisterForm;
import com.xiaomianshi.repository.UserRepository;
import com.xiaomianshi.service.UserService;
import com.xiaomianshi.util.RandomUtils;
import com.xiaomianshi.helper.PrincipalHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhen.yu
 * @since 2018/7/10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUser(String principal) {
        if (PrincipalHelper.isMobile(principal)) {
            return userRepository.findByMobile(principal);
        } else if (PrincipalHelper.isEmail(principal)) {
            return userRepository.findByEmail(principal);
        }
        return userRepository.findByUserName(principal);
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

        public static UserBuilder create() {
            return new UserBuilder();
        }

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setSalt(String salt) {
            this.salt = salt;
            return this;
        }

        public User build() {
            User user = new User();
            user.setUsername(username);
            user.setMobile(mobile);
            user.setEmail(email);
            user.setPassword(password);
            user.setSalt(salt);
            return user;
        }
    }

}
