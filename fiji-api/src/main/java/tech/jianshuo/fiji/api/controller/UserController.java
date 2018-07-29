package tech.jianshuo.fiji.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.jianshuo.fiji.biz.service.UserService;

/**
 * @author zhen.yu
 * Created on 2018-07-26
 */
@RestController
public class UserController extends FijiController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public void test() {
        userService.findUser("zhen");
    }

}
