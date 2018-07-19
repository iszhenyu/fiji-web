package tech.jianshuo.fiji.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tech.jianshuo.fiji.core.aop.LoggerManager;

/**
 * @author zhen.yu
 * @since 2018/7/12
 */
@Controller
public class ViewController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @LoggerManager(desc = "登陆页面")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @LoggerManager(desc = "注册页面")
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

}