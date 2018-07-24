package tech.jianshuo.fiji.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.jianshuo.fiji.biz.dao.UserDao;
import tech.jianshuo.fiji.biz.dto.RegisterDo;
import tech.jianshuo.fiji.biz.helper.PasswordHelper;
import tech.jianshuo.fiji.biz.helper.PrincipalHelper;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.biz.service.UserService;
import tech.jianshuo.fiji.common.util.RandomUtils;
import tech.jianshuo.fiji.common.util.TimeUtils;
import tech.jianshuo.fiji.core.exception.ValidationException;

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

    public User registerUser(RegisterDo regDo) {
        User user = findUser(regDo.getUsername());
        if (user != null) {
            throw new ValidationException("用户已存在");
        }
        UserBuilder builder = UserBuilder.create();
        builder.setUsername(regDo.getUsername());
        builder.setPassword(regDo.getPassword());
        String salt = RandomUtils.randomNumeric(4);
        builder.setSalt(salt);
        user = builder.build();
        userDao.insert(user);
        return user;
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
            user.setDeletedAt(0L);
            user.setCreateTime(TimeUtils.currentTime());
            user.setLastModifyTime(TimeUtils.currentTime());
            return user;
        }
    }

}
