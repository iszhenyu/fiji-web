package tech.jianshuo.fiji.biz.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tech.jianshuo.fiji.biz.model.user.UserRole;
import tech.jianshuo.fiji.core.orm.BaseMapper;

/**
 * @author zhen.yu
 * @since 2018/7/21
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Select("select * from fj_user_role where role_id=#{roleId}")
    List<UserRole> findByRoleId(Long roleId);

    @Select("select * from fj_user_role where user_id=#{userId}")
    List<UserRole> findByUserId(Long userId);

}
