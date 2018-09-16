package tech.jianshuo.fiji.biz.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.fiji.biz.model.user.Role;
import tech.jianshuo.fiji.biz.persistence.RoleDao;
import tech.jianshuo.fiji.biz.persistence.UserRoleDao;
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
    private RoleDao roleDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<Role> loadRolesByUserId(Long userId) {
        List<Long> roleIds = userRoleDao.findRoleIdsByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(roleDao.findByIds(roleIds).values());
    }

}
