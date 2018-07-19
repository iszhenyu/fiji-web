package tech.jianshuo.service;

import tech.jianshuo.form.RegisterForm;
import tech.jianshuo.model.user.User;

/**
 * @author zhen.yu
 * @since 2018/7/10
 */
public interface UserService {

    User findUser(String principal);

    User registerUser(RegisterForm form);

}
