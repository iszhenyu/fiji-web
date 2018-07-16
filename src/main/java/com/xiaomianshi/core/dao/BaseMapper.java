package com.xiaomianshi.core.dao;

import java.util.Collection;
import java.util.List;

/**
 * @author yuzhen
 * Created on 2018-07-15
 */
public interface BaseMapper<T, K> {

    T findById(K id);

    List<T> findByIds(Collection<K> ids);

    List<T> findAll();

    void insert(T model);

    void batchInsert(Collection<T> models);

    int update(T model);

    void remove(T model);

    void removeById(K id);

    void batchRemove(Collection<T> models);

}