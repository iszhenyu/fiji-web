package tech.jianshuo.fiji.core.orm;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author yuzhen
 * Created on 2018-07-15
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}