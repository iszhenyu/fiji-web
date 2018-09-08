package tech.jianshuo.fiji.security;

import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * @author zhen.yu
 * @since 2017/6/6
 */
public interface SecurityConstants {
    String PASSWORD_RETRY_CACHE = "FijiPasswordRetryCache";
    String AUTHORIZATION_CACHE = "FijiAuthorizationCache";
    String AUTHENTICATION_CACHE = "FijiAuthenticationCache";
    String SESSION_CACHE = "FijiSessionCache";

    String HASH_NAME = Sha256Hash.ALGORITHM_NAME;
    int HASH_TIMES = 2;
}
