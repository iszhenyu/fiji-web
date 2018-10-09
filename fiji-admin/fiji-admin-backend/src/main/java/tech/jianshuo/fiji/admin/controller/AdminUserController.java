package tech.jianshuo.fiji.admin.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.jianshuo.fiji.admin.form.AdminUserForm;
import tech.jianshuo.fiji.admin.form.RegisterForm;
import tech.jianshuo.fiji.admin.service.AdminUserService;
import tech.jianshuo.fiji.admin.service.RoleService;
import tech.jianshuo.fiji.biz.model.admin.AdminRole;
import tech.jianshuo.fiji.biz.model.admin.AdminUser;
import tech.jianshuo.fiji.common.util.NumberUtils;
import tech.jianshuo.fiji.core.exception.NotFoundException;
import tech.jianshuo.fiji.core.exception.ValidationException;
import tech.jianshuo.fiji.core.model.page.Pagination;
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

    @GetMapping("/admin_users")
    public ResponseVo getByPage(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        Pagination<AdminUser> userPagination = adminUserService.loadAllAdminUsersWithRolesByPage(pageNo, pageSize);
        return ResponseVo.success(userPagination);
    }

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
        List<String> permissions = new ArrayList<>();
        permissions.add("article:list");
        permissions.add("article:add");
        permissions.add("article:update");
        permissions.add("admin_user:list");
        permissions.add("admin_user:add");
        permissions.add("admin_user:update");
        permissions.add("role:list");
        permissions.add("role:add");
        permissions.add("role:update");
        result.put("permissions", permissions);
        return ResponseVo.success(result);
    }

    @PostMapping("/admin_user/add")
    public ResponseVo add(@Validated AdminUserForm form, BindingResult bindingResult) {
        this.validateForm(bindingResult);
        if (NumberUtils.isPositiveNumber(form.getUserId())) {
            throw new ValidationException("该用户已经存在");
        }
        if (StringUtils.isBlank(form.getPassword())) {
            throw new ValidationException("用户密码不能为空");
        }
        if (form.getRoleIds() == null || form.getRoleIds().length == 0) {
            throw new ValidationException("用户角色不能为空");
        }
        AdminUser created = adminUserService.createAdminUser(form.getAdminUser(), form.getRoleIds());
        return ResponseVo.success(created);
    }

    @PostMapping("/admin_user/update")
    public ResponseVo update(AdminUser user) {
        return ResponseVo.success();
    }

    @DeleteMapping("/admin_user/{userId}")
    public ResponseVo delete(@PathVariable long userId) {
        return ResponseVo.success();
    }

}
