package tech.jianshuo.fiji.biz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.jianshuo.fiji.biz.dao.mapper.RoleMapper;
import tech.jianshuo.fiji.biz.model.user.Role;
import tech.jianshuo.fiji.core.orm.BaseDao;
import tech.jianshuo.fiji.core.orm.BaseMapper;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Component
public class RoleDao extends BaseDao<Role, Long> {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BaseMapper<Role> getMapper() {
        return roleMapper;
    }
}
