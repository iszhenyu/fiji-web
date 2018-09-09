package tech.jianshuo.fiji.biz.service;

import java.util.List;

import tech.jianshuo.fiji.biz.model.user.User;

/**
 * @author zhen.yu
 * @since 2018/7/10
 */
public interface UserService {

    /**
     * 根据principal获取用户
     * @param principal mobile or email or username
     * @return User
     */
    User loadUserByPrincipal(String principal);

    List<User> loadUsersByRoleId(Long roleId);

    User createUser(User user);

}
