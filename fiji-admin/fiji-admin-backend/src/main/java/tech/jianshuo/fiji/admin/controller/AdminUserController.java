package tech.jianshuo.fiji.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.jianshuo.fiji.admin.service.AdminUserService;
import tech.jianshuo.fiji.admin.service.RoleService;
import tech.jianshuo.fiji.biz.model.admin.AdminRole;
import tech.jianshuo.fiji.biz.model.admin.AdminUser;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.core.exception.NotFoundException;
import tech.jianshuo.fiji.core.vo.ResponseVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
@RestController
@RequestMapping("/admin/rest")
public class AdminUserController extends BaseAdminController {

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/admin_user/{userId}")
    public ResponseVo get(@PathVariable long userId) {
        AdminUser user = adminUserService.loadAdminUserById(userId);
        if (user == null) {
            throw new NotFoundException("用户不存在");
        }
        List<AdminRole> roles = roleService.loadRolesByUserId(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("roles", roles.stream().map(AdminRole::getName).collect(Collectors.toList()));
        List<String> menus = new ArrayList<>();
        menus.add("role");
        menus.add("user");
        menus.add("article");
        result.put("menus", menus);
        return ResponseVo.success(result);
    }

    @PostMapping
    public ResponseVo add(User user) {
        return ResponseVo.success();
    }

    @PutMapping
    public ResponseVo update(User user) {
        return ResponseVo.success();
    }

    @DeleteMapping("/admin_user/{userId}")
    public ResponseVo delete(@PathVariable long userId) {
        return ResponseVo.success();
    }

}