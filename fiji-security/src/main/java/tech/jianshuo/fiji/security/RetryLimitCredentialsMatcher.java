package tech.jianshuo.fiji.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * Created by zhen.yu on 2017/3/13.
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

	private Cache<String, Integer> passwordRetryCache;
	private static final int RETRY_TIMES = 5;

	RetryLimitCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache(SecurityConstants.PASSWORD_RETRY_CACHE);
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		//retry count + 1
		Integer retryCount = passwordRetryCache.get(username);
		if (retryCount == null) {
			retryCount = 0;
			passwordRetryCache.put(username, retryCount);
		} else {
			passwordRetryCache.put(username, retryCount + 1);
		}
		if (retryCount > RETRY_TIMES) {
			throw new ExcessiveAttemptsException("您已连续错误" + RETRY_TIMES + "次！请10分钟后再试");
		}

		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			passwordRetryCache.remove(username);
		} else {
			throw new IncorrectCredentialsException("密码错误");
		}
		return true;
	}

}
