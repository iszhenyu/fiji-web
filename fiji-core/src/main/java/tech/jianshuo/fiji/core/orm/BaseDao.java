package tech.jianshuo.fiji.core.orm;

import java.util.Collection;
import java.util.List;

import tech.jianshuo.fiji.core.model.BaseModel;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
public interface BaseDao<T extends BaseModel, K> {
    
    List<T> findByIds(Collection<K> ids);

    List<T> findAll();

    K insert(T model);

    int batchInsert(Collection<T> models);

    int deleteById(K id);

    int delete(T model);

    int batchDelete(Collection<T> models);

    int update(T model);

    int batchUpdate(Collection<T> models);
}
