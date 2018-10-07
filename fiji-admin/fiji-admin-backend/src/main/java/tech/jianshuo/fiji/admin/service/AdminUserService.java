package tech.jianshuo.fiji.admin.service;

import java.util.List;

import tech.jianshuo.fiji.biz.model.admin.AdminUser;
import tech.jianshuo.fiji.core.model.page.Pagination;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
public interface AdminUserService {

    Pagination<AdminUser> loadAllAdminUsersWithRolesByPage(int pageNo, int pageSize);

    AdminUser loadAdminUserById(Long userId);

    /**
     * 获取某个角色下的所有用户
     * @param roleId 角色ID
     * @return 全部用户的列表
     */
    List<AdminUser> loadAdminUsersByRoleId(Long roleId);

    /**
     * 根据用户名、手机号、邮箱获取用户
     * @param principal 用户名、手机号、邮箱
     * @return 用户实例
     */
    AdminUser loadAdminUserByPrincipal(String principal);

    /**
     * 新增用户
     * @param user 用户
     * @return AdminUser
     */
    AdminUser createAdminUser(AdminUser user);
}
