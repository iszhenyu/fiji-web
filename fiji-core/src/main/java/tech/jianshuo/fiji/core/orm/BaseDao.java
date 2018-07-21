package tech.jianshuo.fiji.core.orm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tech.jianshuo.fiji.core.model.BaseModel;

/**
 * @author yuzhen
 * Created on 2018-07-15
 */
public abstract class BaseDao<T extends BaseModel, K>  {

    public abstract BaseMapper<T> getMapper();

    public T findById(K id) {
        return getMapper().selectByPrimaryKey(id);
    }

    public List<T> findByIds(Collection<K> ids) {
        List<T> result = new ArrayList<>(ids.size());
        for (K id: ids) {
            result.add(findById(id));
        }
        return result;
    }

    public List<T> findAll() {
        return getMapper().selectAll();
    }

    public T insert(T model) {
        int num = getMapper().insert(model);
        return num == 0 ? null : model;
    }

    public int batchInsert(Collection<T> models) {
        return getMapper().insertList(new ArrayList<>(models));
    }

    public int removeById(K id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    public int remove(T model) {
        return getMapper().delete(model);
    }

    public int batchRemove(Collection<T> models) {
        int num = 0;
        for (T model : models) {
            num += remove(model);
        }
        return num;
    }

    public int update(T model) {
        return getMapper().updateByPrimaryKey(model);
    }

    public int batchUpdate(Collection<T> models) {
        int num = 0;
        for (T model : models) {
            num += update(model);
        }
        return num;
    }

}