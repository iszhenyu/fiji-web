package tech.jianshuo.fiji.biz.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.core.orm.BaseMapper;

/**
 * @author zhen.yu
 * @since 2018/7/17
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from fj_user where username=#{username}")
    User findByUsername(String username);

    @Select("select * from fj_user where mobile=#{mobile}")
    User findByMobile(String mobile);

    @Select("select * from fj_user where email=#{email}")
    User findByEmail(String email);

    @Select("select u.* from fj_user u INNER JOIN fj_user_role ur ON ur.user_id=u.id WHERE ur.role_id=#{roleId}")
    List<User> findByRoleId(Long roleId);

    @Update("UPDATE fj_user SET mobile=#{mobile} WHERE id=#{id}")
    void updateMobile(User user);

}
