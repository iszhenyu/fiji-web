package tech.jianshuo.fiji.security;

import java.util.Objects;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import tech.jianshuo.fiji.biz.model.user.User;
import tech.jianshuo.fiji.biz.service.UserService;
import tech.jianshuo.fiji.security.service.PasswordService;

/**
 * Created by xiaoz on 2017/5/9.
 */
public class SecurityRealm extends AuthorizingRealm {

	private UserService userService;
	private PasswordService passwordService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPasswordService(PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();

		// Null username is invalid
		if (username == null) {
			throw new AccountException("Null usernames are not allowed by this realm.");
		}

		String password = "";
		if (upToken.getPassword() != null) {
			password = new String(upToken.getPassword());
		}

		User user = userService.findUser(username.trim());
		if (user == null) {
			throw new UnknownAccountException("No account found for user [" + username + "]");
		}

		String encryptedPassword = passwordService.encryptPassword(password, user.getSalt());

		if (!Objects.equals(encryptedPassword, user.getPassword())) {
			throw new AuthenticationException("Wrong Password for user [" + username + "]");
		}

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				username, encryptedPassword.toCharArray(), getName());
		info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
		return info;
	}
}
