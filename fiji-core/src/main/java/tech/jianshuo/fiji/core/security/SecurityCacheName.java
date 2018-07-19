package tech.jianshuo.fiji.core.security;

/**
 * @author zhen.yu
 * @since 2017/6/6
 */
public interface SecurityCacheName {
    String PASSWORD_RETRY_CACHE = "shiroPasswordRetryCache";
    String AUTHORIZATION_CACHE = "shiroAuthorizationCache";
    String AUTHENTICATION_CACHE = "shiroAuthenticationCache";
    String SESSION_CACHE = "shiroSessionCache";
}
