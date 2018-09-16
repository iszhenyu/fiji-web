package tech.jianshuo.fiji.admin.service;

import tech.jianshuo.fiji.biz.model.user.Role;
import tech.jianshuo.fiji.biz.service.RoleService;
import tech.jianshuo.fiji.core.model.page.Pagination;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
public interface AdminRoleService extends RoleService {

    Pagination<Role> loadAllRolesByPage(int currentPage, int pageSize);

}
