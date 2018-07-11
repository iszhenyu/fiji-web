package com.xiaomianshi.service;

import com.xiaomianshi.entity.user.User;
import com.xiaomianshi.form.RegisterForm;

/**
 * @author zhen.yu
 * @since 2018/7/10
 */
public interface UserService {

    User findUser(String principal);

    User registerUser(RegisterForm form);

}
