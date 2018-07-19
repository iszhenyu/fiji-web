package tech.jianshuo.dao;

import com.jianshuo.core.dao.BaseDao;
import com.jianshuo.core.dao.BaseMapper;
import com.jianshuo.dao.mapper.UserMapper;
import com.jianshuo.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.jianshuo.core.dao.BaseDao;
import tech.jianshuo.core.dao.BaseMapper;
import tech.jianshuo.dao.mapper.UserMapper;
import tech.jianshuo.model.user.User;

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
