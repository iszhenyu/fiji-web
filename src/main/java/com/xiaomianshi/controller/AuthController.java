package com.xiaomianshi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhen.yu
 * @since 2018/6/30
 */
@RestController
public class AuthController {

    @RequestMapping("/")
    public String sayHello() {
        return "Hello,World!";
    }
}
