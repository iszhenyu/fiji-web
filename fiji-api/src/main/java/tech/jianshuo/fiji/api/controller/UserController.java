package tech.jianshuo.fiji.api.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tech.jianshuo.fiji.api.form.RegisterForm;
import tech.jianshuo.fiji.biz.dto.RegisterDo;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.biz.service.UserService;
import tech.jianshuo.fiji.core.exception.ValidationException;
import tech.jianshuo.fiji.core.validator.annotation.NotEmpty;

/**
 * @author zhen.yu
 * @since 2018/6/30
 */
@RestController
@RequestMapping("/user")
public class UserController extends FijiController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@Validated RegisterForm form, BindingResult bindingResult) {
        this.validateForm(bindingResult);
        if (!form.getPassword().equals(form.getRePassword())) {
            throw new ValidationException("两次密码不一致");
        }
        RegisterDo registerDo = new RegisterDo();
        registerDo.setUsername(form.getUsername());
        registerDo.setPassword(form.getPassword());
        userService.registerUser(registerDo);
        return "注册成功";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@NotEmpty(message = "用户名不能为空") String username,
                      @NotEmpty(message = "密码不能为空") String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        try {
            // 根据token类型, 这里实际是FijiRealm执行的登录
            subject.login(token);
        } catch (AuthenticationException e) {
            throw new ValidationException("用户名或密码错误");
        }
        return userService.findUser(username);
    }

    @RequestMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "登出成功";
    }
}