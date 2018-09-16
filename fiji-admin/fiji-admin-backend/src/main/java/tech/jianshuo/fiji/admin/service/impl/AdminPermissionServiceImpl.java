package tech.jianshuo.fiji.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.fiji.admin.service.AdminPermissionService;
import tech.jianshuo.fiji.admin.service.AdminUserService;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.common.util.CollectionUtils;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
@Slf4j
@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {

    @Autowired
    private AdminUserService userService;

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
        List<User> users = userService.loadUsersByRoleId(roleId);
        if (CollectionUtils.isEmpty(users)) {
            return;
        }

        for (User user: users) {
            refreshPermissionsOfUser(user.getId());
        }
    }

}
