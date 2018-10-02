package tech.jianshuo.fiji.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.jianshuo.fiji.admin.service.UserAdminService;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.core.model.page.Pagination;
import tech.jianshuo.fiji.core.vo.ResponseVo;

/**
 * @author zhenyu
 * @date 2018-10-02
 */
@RestController
@RequestMapping("/admin/rest")
public class UserController extends BaseAdminController {

    @Autowired
    private UserAdminService userAdminService;

    @GetMapping("/users")
    public ResponseVo getByPage(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        Pagination<User> userPagination = userAdminService.loadAllUsersByPage(pageNo, pageSize);
        return ResponseVo.success(userPagination);
    }
}
