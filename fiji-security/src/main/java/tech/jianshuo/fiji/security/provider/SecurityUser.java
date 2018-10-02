package tech.jianshuo.fiji.security.provider;

import lombok.Data;

/**
 * @author zhenyu
 * @date 2018-10-02
 */
@Data
public class SecurityUser {
    private String username;
    private String password;
    private String salt;
}
