package tech.jianshuo.fiji.biz.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhen.yu
 * Created on 2018-09-10
 */
public enum UserType {
    /**
     * 未指定用户类别
     */
    UNKNOWN("未知"),
    ROOT("超级管理员"),
    ADMIN("管理员"),
    USER("系统会员")
    ;

    private String desc;

    public String getDesc() {
        return desc;
    }

    UserType(String desc) {
        this.desc = desc;
    }

    private static final Map<String, UserType> MAP = new HashMap<>();
    static {
        for (UserType type: UserType.values()) {
            MAP.put(type.name(), type);
        }
    }

    public static UserType fromName(String type) {
        return MAP.getOrDefault(type, UNKNOWN);
    }

}
