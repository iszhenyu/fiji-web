package com.xiaomianshi.core.security;

import org.apache.shiro.realm.jdbc.JdbcRealm;

/**
 * Created by xiaoz on 2017/5/9.
 */
public class FijiSecurityRealm extends JdbcRealm {

	public FijiSecurityRealm() {
		this.setSaltStyle(SaltStyle.COLUMN);
		this.setPermissionsLookupEnabled(true);
		this.setAuthenticationQuery("select password_hash, password_salt from xms_user where username = ?");
		this.setAuthenticationCachingEnabled(true);
		this.setAuthenticationCacheName(SecurityCacheName.AUTHENTICATION_CACHE);
		this.setAuthorizationCachingEnabled(true);
		this.setAuthorizationCacheName(SecurityCacheName.AUTHORIZATION_CACHE);
	}

}
