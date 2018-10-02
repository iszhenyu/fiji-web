package tech.jianshuo.fiji.admin.controller;

import javax.security.auth.Subject;
import javax.validation.constraints.NotEmpty;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.jianshuo.fiji.admin.form.RegisterForm;
import tech.jianshuo.fiji.admin.service.AdminPassportService;
import tech.jianshuo.fiji.admin.vo.LoginVo;
import tech.jianshuo.fiji.biz.model.admin.AdminUser;
import tech.jianshuo.fiji.core.exception.ValidationException;
import tech.jianshuo.fiji.core.vo.ResponseVo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhen.yu
 * Created on 2018-09-06
 */
@RestController
@RequestMapping("/admin/rest/auth")
public class AuthController extends BaseAdminController {

    @Autowired
    private AdminPassportService adminPassportService;

    @PostMapping(value = "/register")
    public ResponseVo register(@Validated RegisterForm form, BindingResult bindingResult) {
        this.validateForm(bindingResult);
        if (form.isPwdNotEqualsToRePwd()) {
            throw new ValidationException("两次密码不一致");
        }
        AdminUser user = adminPassportService.registerUser(form.getUsername(), form.getPassword());
        return ResponseVo.success(user);
    }

    @PostMapping(value = "/login")
    public ResponseVo login(@NotEmpty(message = "用户名不能为空") String username,
                            @NotEmpty(message = "密码不能为空") String password) {
        AdminUser user = adminPassportService.loginWithRememberMe(username, password);
        Serializable tokenId = adminPassportService.loadLoginedToken();
        return ResponseVo.success(LoginVo.fromAdminUserAndToken(user, tokenId));
    }

    @PostMapping("/logout")
    public ResponseVo logout() {
        adminPassportService.logoutUser();
        return ResponseVo.success();
    }

}
