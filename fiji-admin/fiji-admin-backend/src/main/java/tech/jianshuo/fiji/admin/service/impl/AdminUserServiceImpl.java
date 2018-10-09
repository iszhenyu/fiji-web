package tech.jianshuo.fiji.admin.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.pagehelper.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.fiji.admin.service.AdminUserService;
import tech.jianshuo.fiji.admin.util.Paginations;
import tech.jianshuo.fiji.biz.constant.UserStatus;
import tech.jianshuo.fiji.biz.helper.PrincipalHelper;
import tech.jianshuo.fiji.biz.model.admin.AdminRole;
import tech.jianshuo.fiji.biz.model.admin.AdminUser;
import tech.jianshuo.fiji.biz.model.admin.AdminUserRole;
import tech.jianshuo.fiji.biz.persistence.AdminRoleDao;
import tech.jianshuo.fiji.biz.persistence.AdminUserDao;
import tech.jianshuo.fiji.biz.persistence.AdminUserRoleDao;
import tech.jianshuo.fiji.common.util.CollectionUtils;
import tech.jianshuo.fiji.common.util.TimeUtils;
import tech.jianshuo.fiji.core.exception.ValidationException;
import tech.jianshuo.fiji.core.model.page.Pagination;
import tech.jianshuo.fiji.security.service.PasswordService;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
@Slf4j
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;
    @Autowired
    private AdminUserRoleDao adminUserRoleDao;
    @Autowired
    private AdminRoleDao adminRoleDao;

    @Autowired
    private PasswordService passwordService;

    @Override
    public Pagination<AdminUser> loadAllAdminUsersWithRolesByPage(int pageNo, int pageSize) {
        Page<AdminUser> page = Paginations.startPage(pageNo, pageSize).doSelectPage(() -> adminUserDao.findAll());
        List<AdminUser> adminUsers = page.getResult();
        if (CollectionUtils.isNotEmpty(adminUsers)) {
            List<Long> adminUserIds = adminUsers.stream().map(AdminUser::getId).collect(Collectors.toList());
            Map<Long, List<Long>> userId2RoleIds = adminUserRoleDao.findRoleIdsByUserIds(adminUserIds);
            Set<Long> roleIds = userId2RoleIds.values().stream().flatMap(List::stream).collect(Collectors.toSet());
            Map<Long, AdminRole> roleMap = adminRoleDao.findByIds(roleIds);
            adminUsers.forEach(user -> {
                if (userId2RoleIds.containsKey(user.getId())) {
                    List<AdminRole> roles = userId2RoleIds.get(user.getId()).stream()
                            .map(roleMap::get)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList());
                    user.setRoles(roles);
                }
            });
        }
        return Paginations.fromPage(page);
    }

    @Override
    public AdminUser loadAdminUserById(Long userId) {
        return adminUserDao.findById(userId);
    }

    @Override
    public List<AdminUser> loadAdminUsersByRoleId(Long roleId) {
        List<Long> userIds = adminUserRoleDao.findUserIdsByRoleId(roleId);
        if (CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(adminUserDao.findByIds(userIds).values());
    }

    @Override
    public AdminUser loadAdminUserByPrincipal(String principal) {
        if (StringUtils.isBlank(principal)) {
            return null;
        }
        if (PrincipalHelper.isMobile(principal)) {
            return adminUserDao.findByMobile(principal);
        } else if (PrincipalHelper.isEmail(principal)) {
            return adminUserDao.findByEmail(principal);
        }
        return adminUserDao.findByUsername(principal);
    }

    @Override
    public AdminUser createAdminUser(AdminUser user, Long[] roleIds) {
        checkUniqueField(user);
        checkRoleIds(roleIds);

        String salt = passwordService.generateSalt();
        user.setSalt(salt);
        String encryptedPassword = passwordService.encryptPassword(user.getPassword(), salt);
        user.setPassword(encryptedPassword);
        user.setStatus(UserStatus.NORMAL);
        user.setDeletedAt(0L);
        long current = TimeUtils.currentTime();
        user.setCreateTime(current);
        user.setLastModifyTime(current);

        adminUserDao.insert(user);

        for (Long roleId: roleIds) {
            AdminUserRole userRole = newAdminUserRole(user.getId(), roleId);
            adminUserRoleDao.insert(userRole);
        }
        return user;
    }

    private AdminUserRole newAdminUserRole(Long userId, Long roleId) {
        AdminUserRole userRole = new AdminUserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRole.setDeletedAt(0L);
        long current = TimeUtils.currentTime();
        userRole.setCreateTime(current);
        userRole.setLastModifyTime(current);
        return userRole;
    }

    private void checkUniqueField(AdminUser user) {
        if (StringUtils.isNoneBlank(user.getUsername())) {
            AdminUser exists = adminUserDao.findByUsername(user.getUsername());
            if (exists != null) {
                throw new ValidationException("用户名已经存在");
            }
        }
        if (StringUtils.isNoneBlank(user.getMobile())) {
            AdminUser exists = adminUserDao.findByMobile(user.getMobile());
            if (exists != null) {
                throw new ValidationException("手机号已经存在");
            }
        }
        if (StringUtils.isNoneBlank(user.getEmail())) {
            AdminUser exists = adminUserDao.findByEmail(user.getEmail());
            if (exists != null) {
                throw new ValidationException("邮箱已经存在");
            }
        }
    }

    private void checkRoleIds(Long[] roleIds) {
        List<Long> uniqueRoleIds = Stream.of(roleIds).distinct().collect(Collectors.toList());
        Map<Long, AdminRole> roleMap = adminRoleDao.findByIds(uniqueRoleIds);
        if (uniqueRoleIds.size() != roleMap.size()) {
            throw new ValidationException("角色不存在");
        }
    }

}
