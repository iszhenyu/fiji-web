package tech.jianshuo.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tech.jianshuo.core.dao.BaseMapper;
import tech.jianshuo.model.user.User;

/**
 * @author zhen.yu
 * @since 2018/7/17
 */
@Mapper
public interface UserMapper extends BaseMapper<User, Long> {

    @Override
    @Select("select * from fj_user where id=#{id}")
    User findById(Long id);

    @Select("select * from fj_user where username=#{username}")
    User findByUsername(String username);

    @Select("select * from fj_user where mobile=#{mobile}")
    User findByMobile(String mobile);

    @Select("select * from fj_user where email=#{email}")
    User findByEmail(String email);

    @Update("UPDATE fj_user SET mobile=#{mobile} WHERE id=#{id}")
    void updateMobile(User user);

}
