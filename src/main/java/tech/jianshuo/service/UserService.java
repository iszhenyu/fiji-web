package tech.jianshuo.service;

import com.jianshuo.model.user.User;
import com.jianshuo.form.RegisterForm;

import tech.jianshuo.model.user.User;

/**
 * @author zhen.yu
 * @since 2018/7/10
 */
public interface UserService {

    User findUser(String principal);

    User registerUser(RegisterForm form);

}
