package tech.jianshuo.fiji.biz.persistence.mapper;

import org.apache.ibatis.annotations.Mapper;

import tech.jianshuo.fiji.biz.model.user.Resource;
import tech.jianshuo.fiji.core.orm.BaseMapper;

/**
 * @author zhen.yu
 * @since 2018/7/21
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

}
