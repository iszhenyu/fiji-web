package tech.jianshuo.fiji.biz.persistence.mapper.admin;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tech.jianshuo.fiji.biz.model.admin.AdminRoleResource;
import tech.jianshuo.fiji.core.orm.BaseMapper;

/**
 * @author yuzhen
 * Created on 2018-07-21
 */
@Mapper
public interface AdminRoleResourceMapper extends BaseMapper<AdminRoleResource> {

    @Select("select * from fj_admin_role_resource where role_id=#{roleId}")
    List<AdminRoleResource> findByRoleId(Long roleId);

    @Select("select * from fj_admin_role_resource where role_id in #{roleIds}")
    List<AdminRoleResource> findByRoleIds(Collection<Long> roleIds);
}
