package com.xiaomianshi.dao;

import com.xiaomianshi.core.dao.BaseDao;
import com.xiaomianshi.core.dao.BaseMapper;
import com.xiaomianshi.dao.mapper.UserMapper;
import com.xiaomianshi.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhen.yu
 * @since 2018/7/17
 */
@Component
public class UserDao extends BaseDao<User, Long> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseMapper<User, Long> getMapper() {
        return userMapper;
    }

    public User findById(Long id) {
        return userMapper.findById(id);
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public User findByMobile(String mobile) {
        return userMapper.findByMobile(mobile);
    }

    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    public void updateMobile(User user) {
        userMapper.updateMobile(user);
    }

}
