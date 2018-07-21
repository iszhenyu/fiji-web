package tech.jianshuo.fiji.biz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.jianshuo.fiji.biz.dao.mapper.UserMapper;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.core.orm.BaseDao;
import tech.jianshuo.fiji.core.orm.BaseMapper;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Component
public class UserDao extends BaseDao<User, Long> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseMapper<User> getMapper() {
        return userMapper;
    }

}
