package tech.jianshuo.fiji.biz.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.fiji.biz.model.user.Resource;
import tech.jianshuo.fiji.biz.model.user.Role;
import tech.jianshuo.fiji.biz.persistence.ResourceDao;
import tech.jianshuo.fiji.biz.persistence.RoleResourceDao;
import tech.jianshuo.fiji.biz.service.ResourceService;
import tech.jianshuo.fiji.biz.service.RoleService;
import tech.jianshuo.fiji.common.util.CollectionUtils;
import tech.jianshuo.fiji.common.util.MapUtils;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
@Slf4j
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;
    @Autowired
    private RoleResourceDao roleResourceDao;
    @Autowired
    private RoleService roleService;

    @Override
    public Resource createResource(Resource resource) {
        Long id = resourceDao.insert(resource);
        resource.setId(id);
        return resource;
    }

    @Override
    public List<Resource> loadResourcesByUserId(Long userId) {
        if (userId <= 0) {
            return Collections.emptyList();
        }
        List<Role> roles = roleService.loadRolesByUserId(userId);
        if (CollectionUtils.isEmpty(roles)) {
            return Collections.emptyList();
        }
        List<Long> roleIds = roles.stream()
                .filter(Objects::nonNull)
                .map(Role::getId)
                .collect(Collectors.toList());
        Map<Long, List<Long>> roleResourceMap = roleResourceDao.findResourceIdsByRoleIds(roleIds);
        if (MapUtils.isEmpty(roleResourceMap)) {
            return Collections.emptyList();
        }
        List<Long> resourceIds = roleResourceMap.entrySet().stream()
                .filter(e -> CollectionUtils.isNotEmpty(e.getValue()))
                .flatMap(e -> e.getValue().stream())
                .distinct()
                .collect(Collectors.toList());
        return new ArrayList<>(resourceDao.findByIds(resourceIds).values());
    }

    @Override
    public List<Resource> loadUrlAndPermissions() {
        // TODO
        return null;
    }
}
