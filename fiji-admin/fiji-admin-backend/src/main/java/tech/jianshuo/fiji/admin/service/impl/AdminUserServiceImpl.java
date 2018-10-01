package tech.jianshuo.fiji.admin.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.fiji.admin.service.AdminUserService;
import tech.jianshuo.fiji.admin.util.Paginations;
import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.biz.persistence.UserDao;
import tech.jianshuo.fiji.biz.persistence.AdminUserRoleDao;
import tech.jianshuo.fiji.biz.service.impl.UserServiceImpl;
import tech.jianshuo.fiji.common.util.CollectionUtils;
import tech.jianshuo.fiji.core.model.page.Pagination;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
@Slf4j
@Primary
@Service
public class AdminUserServiceImpl extends UserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserRoleDao userRoleDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Pagination<User> loadAllUsersByPage(int pageNo, int pageSize) {
        Page<User> page = Paginations.startPage(pageNo, pageSize).doSelectPage(() -> userDao.findAll());
        return Paginations.fromPage(page);
    }

    @Override
    public List<User> loadUsersByRoleId(Long roleId) {
        List<Long> userIds = userRoleDao.findUserIdsByRoleId(roleId);
        if (CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(userDao.findByIds(userIds).values());
    }
}
