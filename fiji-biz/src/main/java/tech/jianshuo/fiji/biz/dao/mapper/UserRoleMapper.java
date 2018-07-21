package tech.jianshuo.fiji.biz.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import tech.jianshuo.fiji.biz.model.user.UserRole;
import tech.jianshuo.fiji.core.orm.BaseMapper;

/**
 * @author zhen.yu
 * @since 2018/7/21
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
