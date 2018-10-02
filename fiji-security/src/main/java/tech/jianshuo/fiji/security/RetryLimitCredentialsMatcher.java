package tech.jianshuo.fiji.security;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.ByteSource;

import tech.jianshuo.fiji.security.service.PasswordService;

/**
 * @author zhenyu
 * Created on 2017/3/13.
 */
public class RetryLimitCredentialsMatcher extends SimpleCredentialsMatcher {

	private PasswordService passwordService;
	private Cache<String, Integer> passwordRetryCache;
	private static final int RETRY_TIMES = 5;

	RetryLimitCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache(SecurityConstants.PASSWORD_RETRY_CACHE);
	}

	public void setPasswordService(PasswordService passwordService) {
		this.passwordService = passwordService;
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

		boolean matches = match(token, info);
		if (matches) {
			passwordRetryCache.remove(username);
		} else {
			throw new IncorrectCredentialsException("密码错误");
		}
		return true;
	}

	public boolean match(AuthenticationToken token, AuthenticationInfo info) {
		Object tokenHashedCredentials = hashProvidedCredentials(token, info);
		Object accountCredentials = getCredentials(info);
		return equals(tokenHashedCredentials, accountCredentials);
	}

	private Object hashProvidedCredentials(AuthenticationToken token, AuthenticationInfo info) {
		ByteSource salt = null;
		if (info instanceof SaltedAuthenticationInfo) {
			salt = ((SaltedAuthenticationInfo) info).getCredentialsSalt();
		}
		if (salt == null) {
			throw new AccountException("salt is empty");
		}
		String planTextPassword = new String(toBytes(token.getCredentials()));
		return passwordService.encryptPassword(planTextPassword, salt);
	}
}
