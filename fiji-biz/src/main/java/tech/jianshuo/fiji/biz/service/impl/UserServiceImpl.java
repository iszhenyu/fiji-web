package tech.jianshuo.fiji.biz.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.jianshuo.fiji.biz.dao.UserDao;
import tech.jianshuo.fiji.biz.helper.PrincipalHelper;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.biz.service.UserService;
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

    @Override
    public User addUser(User user) {
        String principal = getPrincipal(user);
        User existUser = findUser(principal);
        if (existUser != null) {
            throw new ValidationException("用户已存在");
        }
        userDao.insert(user);
        return user;
    }

    private String getPrincipal(User user) {
        if (StringUtils.isNotBlank(user.getUsername())) {
            return user.getUsername();
        }
        if (StringUtils.isNotBlank(user.getMobile())) {
            return user.getMobile();
        }
        if (StringUtils.isNotBlank(user.getEmail())) {
            return user.getEmail();
        }
        return null;
    }
}
