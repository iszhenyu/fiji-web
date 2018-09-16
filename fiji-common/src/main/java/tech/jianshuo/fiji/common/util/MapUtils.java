package tech.jianshuo.fiji.common.util;

import java.util.Map;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
public class MapUtils {

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.size() == 0;
    }

    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

}
