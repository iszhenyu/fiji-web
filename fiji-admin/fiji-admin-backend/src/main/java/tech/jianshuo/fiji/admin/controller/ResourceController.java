package tech.jianshuo.fiji.admin.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.jianshuo.fiji.biz.model.user.Resource;
import tech.jianshuo.fiji.core.vo.ResponseVo;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
@RestController
@RequestMapping("/admin/rest/res")
public class ResourceController extends AdminController {

    @GetMapping("/{resourceId}")
    public ResponseVo get(@PathVariable long resourceId) {
        return ResponseVo.success();
    }

    @PostMapping
    public ResponseVo add(Resource resource) {
        return ResponseVo.success();
    }

    @PutMapping
    public ResponseVo update(Resource resource) {
        return ResponseVo.success();
    }

    @DeleteMapping("/{resourceId}")
    public ResponseVo delete(@PathVariable long resourceId) {
        return ResponseVo.success();
    }

}
