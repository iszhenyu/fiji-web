package tech.jianshuo.fiji.biz.persistence;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.jianshuo.fiji.biz.helper.ModelHelper;
import tech.jianshuo.fiji.biz.model.admin.AdminUserRole;
import tech.jianshuo.fiji.biz.persistence.mapper.admin.AdminUserRoleMapper;
import tech.jianshuo.fiji.common.util.CollectionUtils;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
@Component
public class AdminUserRoleDao extends BizDao<AdminUserRole> {

    @Autowired
    private AdminUserRoleMapper adminUserRoleMapper;

    @Override
    public AdminUserRoleMapper getMapper() {
        return adminUserRoleMapper;
    }

    public List<Long> findUserIdsByRoleId(Long roleId) {
        List<AdminUserRole> userRoles = getMapper().findByRoleId(roleId);
        if (CollectionUtils.isEmpty(userRoles)) {
            return Collections.emptyList();
        }
        return userRoles.stream()
                .filter(ModelHelper::isNotDeleted)
                .map(AdminUserRole::getUserId)
                .collect(Collectors.toList());
    }

    public List<Long> findUserIdsByRoleIdIncludeDeleted(Long roleId) {
        List<AdminUserRole> userRoles = getMapper().findByRoleId(roleId);
        if (CollectionUtils.isEmpty(userRoles)) {
            return Collections.emptyList();
        }
        return userRoles.stream().map(AdminUserRole::getUserId).collect(Collectors.toList());
    }

    public List<Long> findRoleIdsByUserId(Long userId) {
        List<AdminUserRole> userRoles = getMapper().findByUserId(userId);
        if (CollectionUtils.isEmpty(userRoles)) {
            return Collections.emptyList();
        }
        return userRoles.stream()
                .filter(ModelHelper::isNotDeleted)
                .map(AdminUserRole::getRoleId)
                .collect(Collectors.toList());
    }

    public List<Long> findRoleIdsByUserIdIncludeDeleted(Long userId) {
        List<AdminUserRole> userRoles = getMapper().findByUserId(userId);
        if (CollectionUtils.isEmpty(userRoles)) {
            return Collections.emptyList();
        }
        return userRoles.stream().map(AdminUserRole::getRoleId).collect(Collectors.toList());
    }

}