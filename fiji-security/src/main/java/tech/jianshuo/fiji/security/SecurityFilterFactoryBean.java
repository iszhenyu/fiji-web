package tech.jianshuo.fiji.security;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.BeanInitializationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhenyu
 * Created on 2017/5/9.
 */
public class SecurityFilterFactoryBean extends ShiroFilterFactoryBean {

	@Override
	protected AbstractShiroFilter createInstance() throws Exception {
		SecurityManager securityManager = getSecurityManager();
		if (securityManager == null) {
			throw new BeanInitializationException("SecurityManager property must be set");
		}

		if (!(securityManager instanceof WebSecurityManager)) {
			throw new BeanInitializationException("The shiro manager done not implement the WebSecurityManager interface");
		}

		FilterChainManager manager = createFilterChainManager();
		PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
		chainResolver.setFilterChainManager(manager);
		return new IgnoreSpecifyExtFilter((WebSecurityManager) securityManager, chainResolver);
	}

	@Override
	public Class getObjectType() {
		return IgnoreSpecifyExtFilter.class;
	}

	private static class IgnoreSpecifyExtFilter extends AbstractShiroFilter {
		private static final Set<String> IGNORE_EXT;
		static {
			IGNORE_EXT = new HashSet<>();
			IGNORE_EXT.add(".jpg");
			IGNORE_EXT.add(".png");
			IGNORE_EXT.add(".gif");
			IGNORE_EXT.add(".js");
			IGNORE_EXT.add(".css");
		}

		IgnoreSpecifyExtFilter(WebSecurityManager webSecurityManager, FilterChainResolver filterChainResolver) {
			super();
			if (webSecurityManager == null) {
				throw new IllegalArgumentException("WebSecurityManager property can not be null");
			}
			setSecurityManager(webSecurityManager);
			if (filterChainResolver != null) {
				setFilterChainResolver(filterChainResolver);
			}
		}

		@Override
		protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			String uri = request.getRequestURI().toLowerCase();
			boolean shouldFilter = true;
			int lastDotIdx;
			if ((lastDotIdx = uri.lastIndexOf(".")) > 0) {
				String ext = uri.substring(lastDotIdx);
				if (IGNORE_EXT.contains(ext)) {
					shouldFilter = false;
				}
			}

			if (shouldFilter) {
				super.doFilterInternal(servletRequest, servletResponse, chain);
			} else {
				chain.doFilter(servletRequest, servletResponse);
			}
		}
	}
}
