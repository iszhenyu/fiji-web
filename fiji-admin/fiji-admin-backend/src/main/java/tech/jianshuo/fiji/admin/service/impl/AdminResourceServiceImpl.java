package tech.jianshuo.fiji.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.fiji.admin.service.AdminResourceService;
import tech.jianshuo.fiji.admin.service.AdminRoleService;
import tech.jianshuo.fiji.biz.model.admin.AdminResource;
import tech.jianshuo.fiji.biz.model.admin.AdminRole;
import tech.jianshuo.fiji.biz.persistence.AdminResourceDao;
import tech.jianshuo.fiji.biz.persistence.AdminRoleResourceDao;
import tech.jianshuo.fiji.common.util.CollectionUtils;
import tech.jianshuo.fiji.common.util.MapUtils;
import tech.jianshuo.fiji.core.model.page.Pagination;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
@Slf4j
@Service
public class AdminResourceServiceImpl implements AdminResourceService {

    @Autowired
    private AdminResourceDao adminResourceDao;
    @Autowired
    private AdminRoleResourceDao adminRoleResourceDao;
    @Autowired
    private AdminRoleService adminRoleService;

    @Override
    public Pagination<AdminResource> loadAllResourcesByPage(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public List<AdminResource> loadResourcesByUserId(Long userId) {
        if (userId <= 0) {
            return Collections.emptyList();
        }
        List<AdminRole> adminRoles = adminRoleService.loadRolesByUserId(userId);
        if (CollectionUtils.isEmpty(adminRoles)) {
            return Collections.emptyList();
        }
        List<Long> roleIds = adminRoles.stream()
                .filter(Objects::nonNull)
                .map(AdminRole::getId)
                .collect(Collectors.toList());
        Map<Long, List<Long>> roleResourceMap = adminRoleResourceDao.findResourceIdsByRoleIds(roleIds);
        if (MapUtils.isEmpty(roleResourceMap)) {
            return Collections.emptyList();
        }
        List<Long> resourceIds = roleResourceMap.entrySet().stream()
                .filter(e -> CollectionUtils.isNotEmpty(e.getValue()))
                .flatMap(e -> e.getValue().stream())
                .distinct()
                .collect(Collectors.toList());
        return new ArrayList<>(adminResourceDao.findByIds(resourceIds).values());
    }

    @Override
    public AdminResource createResource(AdminResource adminResource) {
        Long id = adminResourceDao.insert(adminResource);
        adminResource.setId(id);
        return adminResource;
    }

    @Override
    public List<AdminResource> loadUrlAndPermissions() {
        // TODO
        return null;
    }

}
