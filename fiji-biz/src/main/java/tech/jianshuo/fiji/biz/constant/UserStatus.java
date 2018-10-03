package tech.jianshuo.fiji.biz.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhen.yu
 * Created on 2018-07-17
 */
public enum UserStatus {
    /**
     * 账户正常使用
     */
    NORMAL(1, "正常"),

    /**
     * 账户被禁用
     */
    DISABLE(0, "禁用"),
    ;

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    UserStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static final Map<Integer, UserStatus> MAP = new HashMap<>();
    static {
        for (UserStatus status: UserStatus.values()) {
            MAP.put(status.getCode(), status);
        }
    }

    public static UserStatus fromCode(int code) {
        return MAP.getOrDefault(code, NORMAL);
    }

}
