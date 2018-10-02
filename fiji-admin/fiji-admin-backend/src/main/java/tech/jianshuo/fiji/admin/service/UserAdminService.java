package tech.jianshuo.fiji.admin.service;

import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.biz.service.UserService;
import tech.jianshuo.fiji.core.model.page.Pagination;

/**
 * @author zhenyu
 * @date 2018-10-02
 */
public interface UserAdminService extends UserService {

    /**
     * 分页查询全部用户
     * @param pageNo 第几页
     * @param pageSize 每页个数
     * @return 分页数据
     */
    Pagination<User> loadAllUsersByPage(int pageNo, int pageSize);
}
