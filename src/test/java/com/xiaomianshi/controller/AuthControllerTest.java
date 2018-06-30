package com.xiaomianshi.controller;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @author zhen.yu
 * @since 2018/6/30
 */
public class AuthControllerTest {

    @Test
    public void testSayHello() {
        assertEquals("Hello,World!",new AuthController().sayHello());
    }

}
