package tech.jianshuo.fiji.biz.persistence;

import java.util.Collections;
import java.util.List;

import tech.jianshuo.component.util.CollectionUtils;
import tech.jianshuo.fiji.biz.helper.ModelHelper;
import tech.jianshuo.fiji.biz.model.Model;
import tech.jianshuo.fiji.core.orm.DelegatingDao;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 *
 * 保证三个find方法返回的是非删除的数据
 */
public abstract class AbstractBizDao<T extends Model> extends DelegatingDao<T, Long> {

    @Override
    public T findById(Long id) {
        T model = super.findById(id);
        if (model != null && ModelHelper.isNotDeleted(model)) {
            return model;
        }
        return null;
    }

    @Override
    public List<T> findAll() {
        List<T> result = super.findAll();
        if (CollectionUtils.isEmpty(result)) {
            return Collections.emptyList();
        }
        return ModelHelper.filterNotDeleted(result);
    }
}
