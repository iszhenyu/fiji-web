package tech.jianshuo.fiji.biz.persistence.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tech.jianshuo.fiji.biz.model.admin.AdminUser;
import tech.jianshuo.fiji.core.orm.BaseMapper;

/**
 * @author zhen.yu
 * @since 2018/7/17
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {

    @Select("select * from fj_admin_user where username=#{username}")
    AdminUser findByUsername(String username);

    @Select("select * from fj_admin_user where mobile=#{mobile}")
    AdminUser findByMobile(String mobile);

    @Select("select * from fj_admin_user where email=#{email}")
    AdminUser findByEmail(String email);

    @Update("UPDATE fj_admin_user SET mobile=#{mobile} WHERE id=#{id}")
    void updateMobile(AdminUser user);

}
