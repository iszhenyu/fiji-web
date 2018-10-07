package tech.jianshuo.fiji.admin.service;

import tech.jianshuo.fiji.biz.model.admin.AdminRole;
import tech.jianshuo.fiji.core.model.page.Pagination;

import java.util.List;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
public interface RoleService {

    Pagination<AdminRole> loadAllRolesByPage(int pageNo, int pageSize);

    List<AdminRole> loadAllRoles();

    List<AdminRole> loadRolesByUserId(Long userId);

}
