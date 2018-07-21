package tech.jianshuo.fiji.biz.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import tech.jianshuo.fiji.biz.model.user.Role;
import tech.jianshuo.fiji.core.orm.BaseMapper;

/**
 * @author zhen.yu
 * @since 2018/7/21
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
