package tech.jianshuo.fiji.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.jianshuo.fiji.admin.service.AdminUserService;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.core.model.page.Pagination;
import tech.jianshuo.fiji.core.vo.ResponseVo;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
@RestController
@RequestMapping("/admin/rest/user")
public class UserController extends AdminController {

    @Autowired
    private AdminUserService userService;

    @GetMapping("")
    public ResponseVo getByPage(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        Pagination<User> userPagination = userService.loadAllUsersByPage(pageNo, pageSize);
        return ResponseVo.success(userPagination);
    }

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
