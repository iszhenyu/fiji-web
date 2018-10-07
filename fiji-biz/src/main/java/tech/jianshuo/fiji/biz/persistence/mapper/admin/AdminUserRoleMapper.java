package tech.jianshuo.fiji.biz.persistence.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tech.jianshuo.fiji.biz.model.admin.AdminUserRole;
import tech.jianshuo.fiji.core.orm.BaseMapper;

/**
 * @author zhen.yu
 * @since 2018/7/21
 */
@Mapper
public interface AdminUserRoleMapper extends BaseMapper<AdminUserRole> {

    @Select("select * from fj_admin_user_role where role_id=#{roleId}")
    List<AdminUserRole> findByRoleId(Long roleId);

    @Select("select * from fj_admin_user_role where user_id=#{userId}")
    List<AdminUserRole> findByUserId(Long userId);

    List<AdminUserRole> findByUserIds(@Param("userIds") List<Long> userIds);

}
