package tech.jianshuo.fiji.admin.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.jianshuo.fiji.biz.model.admin.AdminRole;
import tech.jianshuo.fiji.core.vo.ResponseVo;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
@RestController
@RequestMapping("/admin/rest/role")
public class RoleController extends BaseAdminController {

    @GetMapping("/{roleId}")
    public ResponseVo get(@PathVariable long roleId) {
        return ResponseVo.success();
    }

    @PostMapping
    public ResponseVo add(AdminRole adminRole) {
        return ResponseVo.success();
    }

    @PutMapping
    public ResponseVo update(AdminRole adminRole) {
        return ResponseVo.success();
    }

    @DeleteMapping("/{roleId}")
    public ResponseVo delete(@PathVariable long roleId) {
        return ResponseVo.success();
    }

}
