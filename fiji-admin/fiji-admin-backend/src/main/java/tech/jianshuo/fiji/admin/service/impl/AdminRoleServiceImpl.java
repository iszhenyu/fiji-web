package tech.jianshuo.fiji.admin.service.impl;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.fiji.admin.service.AdminRoleService;
import tech.jianshuo.fiji.biz.model.user.Role;
import tech.jianshuo.fiji.biz.service.impl.RoleServiceImpl;
import tech.jianshuo.fiji.core.model.page.Pagination;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
@Slf4j
@Service
public class AdminRoleServiceImpl extends RoleServiceImpl implements AdminRoleService {

    @Override
    public Pagination<Role> loadAllRolesByPage(int currentPage, int pageSize) {
        return null;
    }
}
