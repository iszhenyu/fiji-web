package tech.jianshuo.fiji.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.jianshuo.fiji.admin.service.RoleService;
import tech.jianshuo.fiji.biz.model.admin.AdminRole;
import tech.jianshuo.fiji.core.vo.ResponseVo;

import java.util.List;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
@RestController
@RequestMapping("/admin/rest")
public class RoleController extends BaseAdminController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/role/{roleId}")
    public ResponseVo get(@PathVariable long roleId) {

        return ResponseVo.success();
    }

    @GetMapping("/roles/all")
    public ResponseVo getAll() {
        List<AdminRole> roles = roleService.loadAllRoles();
        return ResponseVo.success(roles);
    }

    @PostMapping("/role/add")
    public ResponseVo add(AdminRole adminRole) {
        return ResponseVo.success();
    }

    @PostMapping("/role/update")
    public ResponseVo update(AdminRole adminRole) {
        return ResponseVo.success();
    }

    @DeleteMapping("/role/{roleId}")
    public ResponseVo delete(@PathVariable long roleId) {
        return ResponseVo.success();
    }

}
