package tech.jianshuo.core.dao;

import java.util.Collection;
import java.util.List;

import tech.jianshuo.core.model.BaseModel;

/**
 * @author yuzhen
 * Created on 2018-07-15
 */
public abstract class BaseDao<T extends BaseModel, K>  {

    public abstract BaseMapper<T, K> getMapper();

    public T findById(K id) {
        return getMapper().findById(id);
    }

    public List<T> findByIds(Collection<K> ids) {
        return getMapper().findByIds(ids);
    }

    public List<T> findAll() {
        return getMapper().findAll();
    }

    public T insert(T model) {
        getMapper().insert(model);
        return model;
    }

    public void batchInsert(Collection<T> models) {
        getMapper().batchInsert(models);
    }

    public void removeById(K id) {
        getMapper().removeById(id);
    }

    public void remove(T model) {
        getMapper().remove(model);
    }

    public void batchRemove(Collection<T> models) {
        getMapper().batchRemove(models);
    }

    public int update(T model) {
        int updatedCount = getMapper().update(model);
        if (updatedCount == 0) {
            throw new RuntimeException("更新失败");
        }
        return updatedCount;
    }

    public void batchUpdate(Collection<T> models) {
        for (T model : models) {
            update(model);
        }
    }

}