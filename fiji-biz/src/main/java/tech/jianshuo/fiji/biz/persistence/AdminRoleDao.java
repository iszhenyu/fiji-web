package tech.jianshuo.fiji.biz.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.jianshuo.fiji.biz.model.admin.AdminRole;
import tech.jianshuo.fiji.biz.persistence.mapper.admin.AdminRoleMapper;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Component
public class AdminRoleDao extends BizDao<AdminRole> {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public AdminRoleMapper getMapper() {
        return adminRoleMapper;
    }

}
