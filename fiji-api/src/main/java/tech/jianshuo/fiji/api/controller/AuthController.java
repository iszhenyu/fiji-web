package tech.jianshuo.fiji.api.controller;

import javax.validation.constraints.NotEmpty;

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
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.biz.service.UserService;
import tech.jianshuo.fiji.core.exception.ValidationException;
import tech.jianshuo.fiji.core.vo.ResponseVo;
import tech.jianshuo.fiji.security.service.SecurityService;

/**
 * @author zhen.yu
 * @since 2018/6/30
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends FijiController {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@Validated RegisterForm form, BindingResult bindingResult) {
        this.validateForm(bindingResult);
        if (form.isPwdNotEqualsToRePwd()) {
            throw new ValidationException("两次密码不一致");
        }
        securityService.registerUser(form.getUsername(), form.getPassword());
        return "注册成功";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseVo login(@NotEmpty(message = "用户名不能为空") String username,
                      @NotEmpty(message = "密码不能为空") String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            throw new ValidationException("用户名或密码错误");
        }
        return ResponseVo.success(userService.findUser(username));
    }

    @RequestMapping("/logout")
    public ResponseVo logout() {
        SecurityUtils.getSubject().logout();
        return ResponseVo.success("登出成功");
    }
}
