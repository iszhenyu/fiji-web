package tech.jianshuo.fiji.biz.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.jianshuo.fiji.biz.persistence.mapper.UserMapper;
import tech.jianshuo.fiji.biz.model.user.User;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Component
public class UserDao extends BizDao<User> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserMapper getMapper() {
        return userMapper;
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

}
