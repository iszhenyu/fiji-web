package com.xiaomianshi.dao;

import com.xiaomianshi.core.dao.BaseDao;
import com.xiaomianshi.core.dao.BaseMapper;
import com.xiaomianshi.dao.mapper.UserMapper;
import com.xiaomianshi.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhen.yu
 * @since 2018/7/17
 */
public class UserDao extends BaseDao<User, Long> {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected BaseMapper<User, Long> getMapper() {
        return userMapper;
    }
}
