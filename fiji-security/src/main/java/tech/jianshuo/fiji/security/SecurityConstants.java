package tech.jianshuo.fiji.security;

import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * @author zhen.yu
 * @since 2017/6/6
 */
public interface SecurityConstants {
    String PASSWORD_RETRY_CACHE = "shiroPasswordRetryCache";
    String AUTHORIZATION_CACHE = "shiroAuthorizationCache";
    String AUTHENTICATION_CACHE = "shiroAuthenticationCache";
    String SESSION_CACHE = "shiroSessionCache";

    String HASH_NAME = Sha256Hash.ALGORITHM_NAME;
    int HASH_TIMES = 2;
}
