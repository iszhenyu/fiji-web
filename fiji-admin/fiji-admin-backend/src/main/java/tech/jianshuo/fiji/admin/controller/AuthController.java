package tech.jianshuo.fiji.admin.controller;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.jianshuo.fiji.admin.form.RegisterForm;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.core.exception.ValidationException;
import tech.jianshuo.fiji.core.vo.ResponseVo;
import tech.jianshuo.fiji.security.service.PassportService;

/**
 * @author zhen.yu
 * Created on 2018-09-06
 */
@RestController
@RequestMapping("/admin/auth")
public class AuthController extends AdminController {

    @Autowired
    private PassportService passportService;

    @PostMapping(value = "register")
    public String register(@Validated RegisterForm form, BindingResult bindingResult) {
        this.validateForm(bindingResult);
        if (form.isPwdNotEqualsToRePwd()) {
            throw new ValidationException("两次密码不一致");
        }
        passportService.registerUser(form.getUsername(), form.getPassword());
        return "注册成功";
    }

    @PostMapping(value = "/login")
    public ResponseVo login(@NotEmpty(message = "用户名不能为空") String username,
                            @NotEmpty(message = "密码不能为空") String password) {
        User user = passportService.loginWithRememberMe(username, password);
        return ResponseVo.success(user);
    }

    @PostMapping("/logout")
    public ResponseVo logout() {
        passportService.logoutUser();
        return ResponseVo.success("登出成功");
    }

}
