package tech.jianshuo.fiji.biz.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.jianshuo.fiji.biz.model.admin.AdminUser;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.biz.persistence.mapper.admin.AdminUserMapper;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Component
public class AdminUserDao extends BizDao<AdminUser> {

    @Autowired
    private AdminUserMapper userMapper;

    @Override
    public AdminUserMapper getMapper() {
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
