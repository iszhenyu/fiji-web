package tech.jianshuo.fiji.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Set;

/**
 * @author zhenyu
 * @date 2018-10-04
 */
public class FijiSecurityFilter extends PathMatchingFilter {

    protected Subject getSubject(ServletRequest request, ServletResponse response) {
        return SecurityUtils.getSubject();
    }

    /**
     * 用户必须登录且具有相应角色或权限才允许访问
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param mappedValue String[]类型的角色
     * @return true 代表允许访问
     * @throws Exception any exception
     */
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        // 是否登录
        if (!subject.isAuthenticated()) {
            return false;
        }

        // 是否具有相应角色
        String[] rolesArray = (String[]) mappedValue;
        if (rolesArray == null || rolesArray.length == 0) {
            // 当前path如果没有指定允许访问的角色，则对所有人放行
            return true;
        }

        Set<String> roles = CollectionUtils.asSet(rolesArray);
        return subject.hasAllRoles(roles);
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return isAccessAllowed(request, response, mappedValue);
    }

}
