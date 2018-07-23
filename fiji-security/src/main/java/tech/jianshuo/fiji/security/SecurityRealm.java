package tech.jianshuo.fiji.security;

import org.apache.shiro.realm.jdbc.JdbcRealm;

/**
 * Created by xiaoz on 2017/5/9.
 */
public class SecurityRealm extends JdbcRealm {

	public SecurityRealm() {
		this.setSaltStyle(SaltStyle.COLUMN);
		this.setPermissionsLookupEnabled(true);
		this.setAuthenticationQuery("select password, salt from fj_user where username = ?");
		this.setAuthenticationCachingEnabled(true);
		this.setAuthenticationCacheName(SecurityCacheName.AUTHENTICATION_CACHE);
		this.setAuthorizationCachingEnabled(true);
		this.setAuthorizationCacheName(SecurityCacheName.AUTHORIZATION_CACHE);
	}

}
