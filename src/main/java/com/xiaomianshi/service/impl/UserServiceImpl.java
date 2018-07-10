package com.xiaomianshi.service.impl;

import com.xiaomianshi.entity.user.User;
import com.xiaomianshi.repository.UserRepository;
import com.xiaomianshi.service.UserService;
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

    public User getUser(String username) {
        if (StringUtils.isMobile(username)) {
            return userDao.getByMobile(username);
        } else if (StringUtils.isEmail(username)) {
            return userDao.getByEmail(username);
        }
        return userDao.getByUsername(username);
    }

    public User registerUser(String principal, String credential) {
        String salt = RandomUtils.randomNumeric(4);
        String hashedPassword = jwtService.encodePasswordWithSalt(credential, salt);
        if (StringUtils.isMobile(principal)) {
            String username = principal + "@";
            return createUser(username, principal, hashedPassword, salt);
        }
        return createUser(principal, null, hashedPassword, salt);
    }

    private User createUser(String username, String mobile, String password, String salt) {
        User user = new User();
        user.setUsername(username);
        user.setMobileNumber(mobile);
        user.setPasswordHash(password);
        user.setPasswordSalt(salt);
        user.setStatus(UserStatus.ACTIVE);
        user.setDeleted(false);
        userDao.insert(user);
        return user;
    }


}
