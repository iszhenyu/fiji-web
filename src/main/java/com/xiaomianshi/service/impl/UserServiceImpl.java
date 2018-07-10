package com.xiaomianshi.service.impl;

import com.xiaomianshi.repository.UserRepository;
import com.xiaomianshi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhen.yu
 * @since 2018/7/10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;



}
