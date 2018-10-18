package tech.jianshuo.component.util;

import java.util.Collection;

/**
 * @author zhen.yu
 * Created on 2018-09-09
 */
public class CollectionUtils {

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

}
