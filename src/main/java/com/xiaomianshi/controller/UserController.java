package com.xiaomianshi.controller;

import com.xiaomianshi.common.exception.ValidationException;
import com.xiaomianshi.common.validator.annotation.NotEmpty;
import com.xiaomianshi.entity.user.User;
import com.xiaomianshi.form.RegisterForm;
import com.xiaomianshi.service.UserService;
import com.xiaomianshi.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhen.yu
 * @since 2018/6/30
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@NotEmpty(message = "用户名不能为空") String username,
                        @NotEmpty(message = "密码不能为空") String password) {
        return "Hello,World!";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseVO register(@Validated RegisterForm form, BindingResult bindingResult) {
        this.validateForm(bindingResult);
        if (!form.getPassword().equals(form.getRePassword())) {
            throw new ValidationException("两次密码不一致");
        }
        User user = userService.registerUser(form);
        if (user == null) {
            return ResponseVO.fail("注册失败");
        }
        return ResponseVO.success();
    }
}
