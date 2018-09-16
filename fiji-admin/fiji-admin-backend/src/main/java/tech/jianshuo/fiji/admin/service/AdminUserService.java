package tech.jianshuo.fiji.admin.service;

import java.util.List;

import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.biz.service.UserService;
import tech.jianshuo.fiji.core.model.page.Pagination;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
public interface AdminUserService extends UserService {

    Pagination<User> loadAllUsersByPage(int pageNo, int pageSize);

    List<User> loadUsersByRoleId(Long roleId);
}
