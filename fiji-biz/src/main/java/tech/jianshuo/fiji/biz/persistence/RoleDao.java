package tech.jianshuo.fiji.biz.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.jianshuo.fiji.biz.model.user.Role;
import tech.jianshuo.fiji.biz.persistence.mapper.RoleMapper;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Component
public class RoleDao extends BizDao<Role> {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleMapper getMapper() {
        return roleMapper;
    }

}
