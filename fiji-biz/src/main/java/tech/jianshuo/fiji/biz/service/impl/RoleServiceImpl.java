package tech.jianshuo.fiji.biz.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.fiji.biz.model.admin.AdminRole;
import tech.jianshuo.fiji.biz.persistence.AdminRoleDao;
import tech.jianshuo.fiji.biz.persistence.AdminUserRoleDao;
import tech.jianshuo.fiji.biz.service.RoleService;
import tech.jianshuo.fiji.common.util.CollectionUtils;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private AdminRoleDao roleDao;
    @Autowired
    private AdminUserRoleDao userRoleDao;

    @Override
    public List<AdminRole> loadRolesByUserId(Long userId) {
        List<Long> roleIds = userRoleDao.findRoleIdsByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(roleDao.findByIds(roleIds).values());
    }

}
