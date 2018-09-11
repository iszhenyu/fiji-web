package tech.jianshuo.fiji.admin.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.core.vo.ResponseVo;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
@RestController
@RequestMapping("/admin/rest/user")
public class UserController extends AdminController {

    @GetMapping("/{userId}")
    public ResponseVo get(@PathVariable long userId) {
        return ResponseVo.success();
    }

    @PostMapping
    public ResponseVo add(User user) {
        return ResponseVo.success();
    }

    @PutMapping
    public ResponseVo update(User user) {
        return ResponseVo.success();
    }

    @DeleteMapping("/{userId}")
    public ResponseVo delete(@PathVariable long userId) {
        return ResponseVo.success();
    }

}
