package tech.jianshuo.fiji.biz.persistence;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.jianshuo.fiji.biz.helper.ModelHelper;
import tech.jianshuo.fiji.biz.model.user.UserRole;
import tech.jianshuo.fiji.biz.persistence.mapper.UserRoleMapper;
import tech.jianshuo.fiji.common.util.CollectionUtils;
import tech.jianshuo.fiji.core.orm.DelegatingDao;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
@Component
public class UserRoleDao extends DelegatingDao<UserRole, Long> {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserRoleMapper getMapper() {
        return userRoleMapper;
    }

    public List<Long> findUserIdsByRoleIdIncludeDeleted(Long roleId) {
        List<UserRole> userRoles = getMapper().findUserIdsByRoleId(roleId);
        if (CollectionUtils.isEmpty(userRoles)) {
            return Collections.emptyList();
        }
        return userRoles.stream().map(UserRole::getUserId).collect(Collectors.toList());
    }

    public List<Long> findUserIdsByRoleId(Long roleId) {
        List<UserRole> userRoles = getMapper().findUserIdsByRoleId(roleId);
        if (CollectionUtils.isEmpty(userRoles)) {
            return Collections.emptyList();
        }
        return userRoles.stream()
                .filter(ModelHelper::isNotDeleted)
                .map(UserRole::getUserId)
                .collect(Collectors.toList());
    }

}
