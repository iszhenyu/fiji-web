package tech.jianshuo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.jianshuo.core.exception.ValidationException;
import tech.jianshuo.dao.UserDao;
import tech.jianshuo.form.RegisterForm;
import tech.jianshuo.helper.PasswordHelper;
import tech.jianshuo.helper.PrincipalHelper;
import tech.jianshuo.model.user.User;
import tech.jianshuo.service.UserService;
import tech.jianshuo.util.RandomUtils;
import tech.jianshuo.util.TimeUtils;

/**
 * @author zhen.yu
 * @since 2018/7/10
 */
@Service
public class UserServiceImpl extends FijiService implements UserService {

    @Autowired
    private UserDao userDao;

    public User findUser(String principal) {
        if (PrincipalHelper.isMobile(principal)) {
            return userDao.findByMobile(principal);
        } else if (PrincipalHelper.isEmail(principal)) {
            return userDao.findByEmail(principal);
        }
        return userDao.findByUsername(principal);
    }

    public User registerUser(RegisterForm form) {
        User user = userDao.findByUsername(form.getUsername());
        if (user != null) {
            throw new ValidationException("用户已存在");
        }
        UserBuilder builder = UserBuilder.create();
        builder.setUsername(form.getUsername());
        builder.setPassword(form.getPassword());
        String salt = RandomUtils.randomNumeric(4);
        builder.setSalt(salt);
        return userDao.insert(builder.build());
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