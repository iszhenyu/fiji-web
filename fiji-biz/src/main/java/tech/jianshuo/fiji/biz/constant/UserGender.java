package tech.jianshuo.fiji.biz.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhen.yu
 * Created on 2018-09-10
 */
public enum UserGender {
    /**
     * 未知
     */
    UNKNOWN(-1, ""),
    /**
     * 男
     */
    MALE(1, "男"),
    /**
     * 女
     */
    FEMALE(0, "女")
    ;

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    UserGender(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static final Map<Integer, UserGender> CODE_MAP = new HashMap<>();
    private static final Map<String, UserGender> NAME_MAP = new HashMap<>();
    static {
        for (UserGender gender: UserGender.values()) {
            CODE_MAP.put(gender.getCode(), gender);
            NAME_MAP.put(gender.name(), gender);
        }
    }

    public static UserGender fromCode(int code) {
        return CODE_MAP.getOrDefault(code, UNKNOWN);
    }

    public static UserGender fromName(String name) {
        return NAME_MAP.getOrDefault(name, UNKNOWN);
    }

}
