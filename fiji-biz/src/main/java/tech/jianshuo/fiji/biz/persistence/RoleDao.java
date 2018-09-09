package tech.jianshuo.fiji.biz.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.jianshuo.fiji.biz.model.user.Role;
import tech.jianshuo.fiji.biz.persistence.mapper.RoleMapper;
import tech.jianshuo.fiji.core.orm.DelegatingDao;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Component
public class RoleDao extends DelegatingDao<Role, Long> {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleMapper getMapper() {
        return roleMapper;
    }

    public List<Role> findRolesByUserId(Long userId) {
        return getMapper().findRolesByUserId(userId);
    }
}
