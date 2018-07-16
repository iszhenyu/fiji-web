package com.xiaomianshi.core.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiaomianshi.core.model.BaseModel;

/**
 * @author yuzhen
 * Created on 2018-07-15
 */
public abstract class BaseDao<T extends BaseModel, K>  {

    protected abstract BaseMapper<T, K> getMapper();

    @Autowired
    private SqlSession sqlSession;

    public T findById(K id) {
        return sqlSession.selectOne("selectCityById", id);
//        return getMapper().findById(id);
    }

    public List<T> findByIds(Collection<K> ids) {
        return getMapper().findByIds(ids);
    }

    public List<T> findAll() {
        return getMapper().findAll();
    }

    public void insert(T model) {
        getMapper().insert(model);
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

    //更新乐观锁
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