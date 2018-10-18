package tech.jianshuo.fiji.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.component.util.CollectionUtils;
import tech.jianshuo.fiji.admin.service.PermissionService;
import tech.jianshuo.fiji.admin.service.AdminUserService;
import tech.jianshuo.fiji.biz.model.admin.AdminUser;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private AdminUserService adminUserService;

    @Override
    public void refreshPermissions() {
        // TODO
    }

    @Override
    public void refreshPermissionsOfUser(Long userId) {
        // TODO
    }

    @Override
    public void refreshPermissionsOfRole(Long roleId) {
        List<AdminUser> users = adminUserService.loadAdminUsersByRoleId(roleId);
        if (CollectionUtils.isEmpty(users)) {
            return;
        }

        for (AdminUser user: users) {
            refreshPermissionsOfUser(user.getId());
        }
    }

}
