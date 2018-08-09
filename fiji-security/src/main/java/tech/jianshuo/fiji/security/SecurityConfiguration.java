package tech.jianshuo.fiji.security;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import tech.jianshuo.fiji.biz.service.UserService;
import tech.jianshuo.fiji.security.cache.SpringRedisCacheManager;
import tech.jianshuo.fiji.security.service.PasswordService;

/**
 * Created by zhen.yu on 2017/5/9.
 */
@Configuration
public class SecurityConfiguration {

	private Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),
	 * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
	 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
	 * 设置setProxyTargetClass(true) 会启用CGLIB
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		autoProxyCreator.setProxyTargetClass(true);
		return autoProxyCreator;
	}

	/**
	 * 开启shiro aop注解支持.
	 * 使用代理方式;
	 * 所以需要开启代码支持;
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	public CacheManager shiroRedisCacheManager(org.springframework.cache.CacheManager cacheManager) {
		SpringRedisCacheManager springCacheManagerWrapper = new SpringRedisCacheManager();
		springCacheManagerWrapper.setSpringCacheManager(cacheManager);
		return springCacheManagerWrapper;
	}

	@Bean
	public RetryLimitCredentialsMatcher credentialsMatcher(CacheManager cacheManager, PasswordService passwordService) {
		RetryLimitCredentialsMatcher credentialsMatcher = new RetryLimitCredentialsMatcher(cacheManager);
		credentialsMatcher.setPasswordService(passwordService);
		return credentialsMatcher;
	}

	@Bean
	public SecurityRealm shiroRealm(UserService userService,
									CredentialsMatcher credentialsMatcher) {
		SecurityRealm realm = new SecurityRealm();
		realm.setUserService(userService);
		realm.setCredentialsMatcher(credentialsMatcher);
		return realm;
	}

	@Bean
	public SecurityManager securityManager(CacheManager cacheManager,
										   Realm realm,
										   SessionManager sessionManager) {
		DefaultSecurityManager sm = new DefaultWebSecurityManager();
		sm.setRealm(realm);
		sm.setCacheManager(cacheManager);
		sm.setSessionManager(sessionManager);
		sm.setRememberMeManager(rememberMeManager());
		return sm;
	}

	/** Session 相关 **/

	@Bean
	public DefaultWebSessionManager sessionManager(SessionDAO sessionDAO) {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(1800000);
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setSessionIdCookie(sessionIdCookie());
		sessionManager.setSessionDAO(sessionDAO);
		return sessionManager;
	}

//	@Bean
//	public SessionValidationScheduler sessionValidationScheduler() {
//		QuartzSessionValidationScheduler scheduler = new QuartzSessionValidationScheduler();
//		scheduler.setSessionValidationInterval(1800000);
//		scheduler.setSessionManager(sessionManager());
//		return scheduler;
//	}

	@Bean
	public SimpleCookie sessionIdCookie(){
		SimpleCookie cookie = new SimpleCookie();
		cookie.setHttpOnly(true);
		cookie.setMaxAge(259200);
		cookie.setName("sid");
		return cookie;
	}

	@Bean
	public SessionDAO sessionDAO(CacheManager cacheManager) {
		EnterpriseCacheSessionDAO dao = new EnterpriseCacheSessionDAO();
		dao.setSessionIdGenerator(sessionIdGenerator());
		dao.setCacheManager(cacheManager);
		dao.setActiveSessionsCacheName(SecurityConstants.SESSION_CACHE);
		return dao;
	}

	@Bean
	public SessionIdGenerator sessionIdGenerator() {
		return new JavaUuidSessionIdGenerator();
	}

	/** cookie管理器 **/

	@Bean
	public SimpleCookie rememberMeCookie(){
		//这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		//如果httyOnly设置为true，则客户端不会暴露给客户端脚本代码，使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；
		simpleCookie.setHttpOnly(true);
		//记住我cookie生效时间,默认30天 ,单位秒：60 * 60 * 24 * 30
		simpleCookie.setMaxAge(259200);
		return simpleCookie;
	}

	@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		//rememberme cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度（128 256 512 位），通过以下代码可以获取
		//KeyGenerator keygen = KeyGenerator.getInstance("AES");
		//SecretKey deskey = keygen.generateKey();
		//System.out.println(Base64.encodeToString(deskey.getEncoded()));
		byte[] cipherKey = Base64.decode("rPNqM6uKFCyaL10AK51UkQ==");
		cookieRememberMeManager.setCipherKey(cipherKey);
		cookieRememberMeManager.setCookie(rememberMeCookie());
		return cookieRememberMeManager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		SecurityFilterFactoryBean factoryBean = new SecurityFilterFactoryBean();
		factoryBean.setSecurityManager(securityManager);
		factoryBean.setLoginUrl("/auth/login");
		factoryBean.setSuccessUrl("/");
		factoryBean.setUnauthorizedUrl("/error/401");
		//拦截器.
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/favicon.ico", "anon");
		filterChainDefinitionMap.put("/assets/**", "anon");
		filterChainDefinitionMap.put("/error/**", "anon");
		filterChainDefinitionMap.put("/user/**", "anon");
		filterChainDefinitionMap.put("/auth/login", "anon");
		filterChainDefinitionMap.put("/auth/register", "anon");
		filterChainDefinitionMap.put("/auth/logout", "logout");
		//配置记住我过滤器或认证通过可以访问的地址(当上次登录时，记住我以后，在下次访问/或/index时，可以直接访问，不需要登陆)
		filterChainDefinitionMap.put("/index", "user");
		filterChainDefinitionMap.put("/", "user");
		filterChainDefinitionMap.put("/**", "authc");
		factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return factoryBean;
	}

}
