package tech.jianshuo.fiji.biz.helper;

import tech.jianshuo.fiji.biz.model.Model;

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
}
