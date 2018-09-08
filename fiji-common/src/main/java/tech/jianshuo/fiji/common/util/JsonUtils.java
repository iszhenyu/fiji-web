package tech.jianshuo.fiji.common.util;

import com.alibaba.fastjson.JSON;

/**
 * @author zhen.yu
 * Created on 2018-09-08
 */
public class JsonUtils {
    private static final String EMPTY_JSON = "{}";
    private static final String EMPTY_ARRAY_JSON = "[]";

    private final static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private JsonUtils() {
    }

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T fromJson(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

}
