package tech.jianshuo.fiji.core.orm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.jianshuo.component.util.TimeUtils;
import tech.jianshuo.fiji.core.model.BaseModel;

/**
 * @author yuzhen
 * Created on 2018-07-15
 */
public abstract class DelegatingDao<T extends BaseModel<K>, K> implements BaseDao<T, K> {

    public abstract BaseMapper<T> getMapper();

    public T findById(K id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public Map<K, T> findByIds(Collection<K> ids) {
        Map<K, T> result = new HashMap<>();
        for (K id: ids) {
            T model = findById(id);
            if (model != null) {
                result.put(id, findById(id));
            }
        }
        return result;
    }

    @Override
    public List<T> findAll() {
        return getMapper().selectAll();
    }

    @Override
    public K insert(T model) {
        if (model.getCreateTime() == null || model.getCreateTime() <= 0) {
            long currentTime = TimeUtils.currentTime();
            model.setCreateTime(currentTime);
            model.setLastModifyTime(currentTime);
        }
        int num = getMapper().insert(model);
        return num == 0 ? null : model.getId();
    }

    @Override
    public int batchInsert(Collection<T> models) {
        return getMapper().insertList(new ArrayList<>(models));
    }

    /**
     * 物理删除
     */
    @Override
    public int deleteById(K id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    /**
     * 物理删除
     */
    @Override
    public int delete(T model) {
        return getMapper().delete(model);
    }

    /**
     * 物理删除
     */
    @Override
    public int batchDelete(Collection<T> models) {
        int num = 0;
        for (T model : models) {
            num += delete(model);
        }
        return num;
    }

    /**
     * 逻辑删除
     */
    public int logicDeleteById(K id) {
        T model = findById(id);
        if (model == null) {
            return 0;
        }
        return logicDelete(model);
    }

    /**
     * 逻辑删除
     */
    public int logicDelete(T model) {
        long deleteTime = TimeUtils.currentTime();
        model.setDeletedAt(deleteTime);
        return update(model);
    }

    /**
     * 逻辑删除
     */
    public int batchLogicDelete(Collection<T> models) {
        int num = 0;
        for (T model : models) {
            num += logicDelete(model);
        }
        return num;
    }

    @Override
    public int update(T model) {
        long currentTime = TimeUtils.currentTime();
        model.setLastModifyTime(currentTime);
        return getMapper().updateByPrimaryKey(model);
    }

    @Override
    public int batchUpdate(Collection<T> models) {
        int num = 0;
        for (T model : models) {
            num += update(model);
        }
        return num;
    }

}