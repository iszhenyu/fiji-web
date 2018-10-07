package tech.jianshuo.fiji.admin.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.pagehelper.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.fiji.admin.service.AdminUserService;
import tech.jianshuo.fiji.admin.util.Paginations;
import tech.jianshuo.fiji.biz.helper.PrincipalHelper;
import tech.jianshuo.fiji.biz.model.admin.AdminUser;
import tech.jianshuo.fiji.biz.persistence.AdminUserDao;
import tech.jianshuo.fiji.biz.persistence.AdminUserRoleDao;
import tech.jianshuo.fiji.common.util.CollectionUtils;
import tech.jianshuo.fiji.core.exception.ValidationException;
import tech.jianshuo.fiji.core.model.page.Pagination;

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

    @Override
    public Pagination<AdminUser> loadAllAdminUsersByPage(int pageNo, int pageSize) {
        Page<AdminUser> page = Paginations.startPage(pageNo, pageSize).doSelectPage(() -> adminUserDao.findAll());
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
    public AdminUser createAdminUser(AdminUser user) {
        String principal = getPrincipal(user);
        AdminUser existUser = loadAdminUserByPrincipal(principal);
        if (existUser != null) {
            throw new ValidationException("用户已存在");
        }
        adminUserDao.insert(user);
        return user;
    }

    private String getPrincipal(AdminUser user) {
        if (StringUtils.isNotBlank(user.getUsername())) {
            return user.getUsername();
        }
        if (StringUtils.isNotBlank(user.getMobile())) {
            return user.getMobile();
        }
        if (StringUtils.isNotBlank(user.getEmail())) {
            return user.getEmail();
        }
        return null;
    }
}
