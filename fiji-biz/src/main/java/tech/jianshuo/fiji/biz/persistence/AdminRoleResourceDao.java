package tech.jianshuo.fiji.biz.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.fiji.biz.helper.ModelHelper;
import tech.jianshuo.fiji.biz.model.admin.AdminRoleResource;
import tech.jianshuo.fiji.biz.persistence.mapper.admin.AdminRoleResourceMapper;
import tech.jianshuo.fiji.common.util.CollectionUtils;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
@Slf4j
@Component
public class AdminRoleResourceDao extends AbstractBizDao<AdminRoleResource> {

    @Autowired
    private AdminRoleResourceMapper adminRoleResourceMapper;

    @Override
    public AdminRoleResourceMapper getMapper() {
        return adminRoleResourceMapper;
    }

    public List<Long> findResourceIdsByRoleId(Long roleId) {
        List<AdminRoleResource> adminRoleResources = adminRoleResourceMapper.findByRoleId(roleId);
        if (CollectionUtils.isEmpty(adminRoleResources)) {
            return Collections.emptyList();
        }
        return adminRoleResources.stream()
                .filter(ModelHelper::isNotDeleted)
                .map(AdminRoleResource::getResourcesId)
                .collect(Collectors.toList());
    }

    public Map<Long, List<Long>> findResourceIdsByRoleIds(Collection<Long> roleIds) {
        List<AdminRoleResource> adminRoleResources = adminRoleResourceMapper.findByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(adminRoleResources)) {
            return Collections.emptyMap();
        }
        Map<Long, List<Long>> result = new HashMap<>();
        for (AdminRoleResource rr: adminRoleResources) {
            if (ModelHelper.isDeleted(rr)) {
                continue;
            }
            List<Long> resIds = result.get(rr.getRoleId());
            if (resIds == null) {
                resIds = result.put(rr.getRoleId(), new ArrayList<>());
            }
            if (resIds != null) {
                resIds.add(rr.getResourcesId());
            } else {
                log.error("Map put error!");
            }
        }
        return result;
    }

}
