package tech.jianshuo.fiji.biz.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.jianshuo.fiji.biz.persistence.mapper.UserMapper;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.core.orm.DelegatingDao;
import tech.jianshuo.fiji.core.orm.BaseMapper;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Component
public class UserDao extends DelegatingDao<User, Long> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseMapper<User> getMapper() {
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
