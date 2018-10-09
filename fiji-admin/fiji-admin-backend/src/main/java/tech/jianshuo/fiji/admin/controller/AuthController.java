package tech.jianshuo.fiji.admin.controller;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.jianshuo.fiji.admin.service.AdminPassportService;
import tech.jianshuo.fiji.admin.vo.LoginVo;
import tech.jianshuo.fiji.biz.model.admin.AdminUser;
import tech.jianshuo.fiji.core.vo.ResponseVo;

import java.io.Serializable;

/**
 * @author zhen.yu
 * Created on 2018-09-06
 */
@RestController
@RequestMapping("/admin/rest/auth")
public class AuthController extends BaseAdminController {

    @Autowired
    private AdminPassportService passportService;

    @PostMapping(value = "/login")
    public ResponseVo login(@NotEmpty(message = "用户名不能为空") String username,
                            @NotEmpty(message = "密码不能为空") String password) {
        AdminUser user = passportService.loginWithRememberMe(username, password);
        Serializable tokenId = passportService.loadLoginedToken();
        return ResponseVo.success(LoginVo.fromAdminUserAndToken(user, tokenId));
    }

    @PostMapping("/logout")
    public ResponseVo logout() {
        passportService.logoutUser();
        return ResponseVo.success();
    }

}
