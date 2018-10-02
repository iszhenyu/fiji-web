package tech.jianshuo.fiji.security.provider;

public interface UserProvider {

    /**
     * 获取用户信息
     * @param principal 用户名/手机号/邮箱
     * @return SecurityUser
     */
    SecurityUser provideUser(String principal);
}
