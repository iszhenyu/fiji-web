package tech.jianshuo.fiji.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.component.util.CollectionUtils;
import tech.jianshuo.fiji.admin.service.RoleService;
import tech.jianshuo.fiji.biz.model.admin.AdminRole;
import tech.jianshuo.fiji.biz.persistence.AdminRoleDao;
import tech.jianshuo.fiji.biz.persistence.AdminUserRoleDao;
import tech.jianshuo.fiji.core.model.page.Pagination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
@Slf4j
@Primary
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private AdminRoleDao adminRoleDao;
    @Autowired
    private AdminUserRoleDao adminUserRoleDao;

    @Override
    public Pagination<AdminRole> loadAllRolesByPage(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public List<AdminRole> loadAllRoles() {
        return adminRoleDao.findAll();
    }

    @Override
    public List<AdminRole> loadRolesByUserId(Long userId) {
        List<Long> roleIds = adminUserRoleDao.findRoleIdsByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(adminRoleDao.findByIds(roleIds).values());
    }
}
