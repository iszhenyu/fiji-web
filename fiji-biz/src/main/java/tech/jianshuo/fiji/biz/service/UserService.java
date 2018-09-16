package tech.jianshuo.fiji.biz.service;

import tech.jianshuo.fiji.biz.model.user.User;

/**
 * @author zhen.yu
 * @since 2018/7/10
 */
public interface UserService {

    User loadUserByPrincipal(String principal);

    User createUser(User user);

}
