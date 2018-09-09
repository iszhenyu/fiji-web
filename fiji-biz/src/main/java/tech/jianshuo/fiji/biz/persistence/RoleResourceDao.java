package tech.jianshuo.fiji.biz.persistence;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.jianshuo.fiji.biz.helper.ModelHelper;
import tech.jianshuo.fiji.biz.model.user.RoleResource;
import tech.jianshuo.fiji.biz.persistence.mapper.RoleResourceMapper;
import tech.jianshuo.fiji.common.util.CollectionUtils;
import tech.jianshuo.fiji.core.orm.DelegatingDao;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
@Component
public class RoleResourceDao extends DelegatingDao<RoleResource, Long> {

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public RoleResourceMapper getMapper() {
        return roleResourceMapper;
    }

    public List<Long> findResourceIdsByRoleId(Long roleId) {
        List<RoleResource> roleResources = roleResourceMapper.findByRoleId(roleId);
        if (CollectionUtils.isEmpty(roleResources)) {
            return Collections.emptyList();
        }
        return roleResources.stream()
                .filter(ModelHelper::isNotDeleted)
                .map(RoleResource::getResourcesId)
                .collect(Collectors.toList());
    }



}
