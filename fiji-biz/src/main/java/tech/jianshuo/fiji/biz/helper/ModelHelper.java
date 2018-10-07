package tech.jianshuo.fiji.biz.helper;

import tech.jianshuo.fiji.biz.model.Model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
public class ModelHelper {

    public static boolean isNotDeleted(Model model) {
        return model.getDeletedAt() == null || model.getDeletedAt() <= 0;
    }

    public static boolean isDeleted(Model model) {
        return !isNotDeleted(model);
    }

    public static <T extends Model> T ensureNotDeleted(T model) {
        if (isDeleted(model)) {
            return null;
        }
        return model;
    }

    public static <T extends Model> List<T> filterNotDeleted(Collection<T> models) {
        return models.stream().filter(ModelHelper::isNotDeleted).collect(Collectors.toList());
    }
}
