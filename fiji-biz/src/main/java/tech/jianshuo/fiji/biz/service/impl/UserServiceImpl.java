package tech.jianshuo.fiji.biz.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.fiji.biz.persistence.UserDao;
import tech.jianshuo.fiji.biz.helper.PrincipalHelper;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.biz.persistence.UserRoleDao;
import tech.jianshuo.fiji.biz.service.UserService;
import tech.jianshuo.fiji.common.util.CollectionUtils;
import tech.jianshuo.fiji.core.exception.ValidationException;

/**
 * @author zhen.yu
 * @since 2018/7/10
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public User loadUserByPrincipal(String principal) {
        if (StringUtils.isBlank(principal)) {
            return null;
        }
        if (PrincipalHelper.isMobile(principal)) {
            return userDao.findByMobile(principal);
        } else if (PrincipalHelper.isEmail(principal)) {
            return userDao.findByEmail(principal);
        }
        return userDao.findByUsername(principal);
    }

    @Override
    public List<User> loadUsersByRoleId(Long roleId) {
        List<Long> userIds = userRoleDao.findUserIdsByRoleId(roleId);
        if (CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyList();
        }
        return userDao.findByIds(userIds);
    }

    @Override
    public User createUser(User user) {
        String principal = getPrincipal(user);
        User existUser = loadUserByPrincipal(principal);
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
