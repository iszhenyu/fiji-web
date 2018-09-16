package tech.jianshuo.fiji.biz.persistence;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import tech.jianshuo.fiji.biz.helper.ModelHelper;
import tech.jianshuo.fiji.biz.model.Model;
import tech.jianshuo.fiji.common.util.CollectionUtils;
import tech.jianshuo.fiji.core.orm.DelegatingDao;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
public abstract class BizDao<T extends Model> extends DelegatingDao<T, Long> {

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
        return result.stream()
                .filter(ModelHelper::isNotDeleted)
                .collect(Collectors.toList());
    }
}
