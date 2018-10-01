package tech.jianshuo.fiji.admin.service;

import java.util.List;

import tech.jianshuo.fiji.biz.model.admin.AdminUser;
import tech.jianshuo.fiji.core.model.page.Pagination;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
public interface AdminUserService {

    /**
     * 分页查询全部用户
     * @param pageNo 第几页
     * @param pageSize 每页个数
     * @return 分页数据
     */
    Pagination<AdminUser> loadAllUsersByPage(int pageNo, int pageSize);

    /**
     * 获取某个角色下的所有用户
     * @param roleId 角色ID
     * @return 全部用户的列表
     */
    List<AdminUser> loadUsersByRoleId(Long roleId);

    /**
     * 根据用户名、手机号、邮箱获取用户
     * @param principal 用户名、手机号、邮箱
     * @return 用户实例
     */
    AdminUser loadUserByPrincipal(String principal);

    /**
     * 新增用户
     * @param user 用户
     * @return AdminUser
     */
    AdminUser createUser(AdminUser user);
}
