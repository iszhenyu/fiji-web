package tech.jianshuo.fiji.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author zhenyu
 * @date 2018-10-04
 */
public class FijiSecurityFilter extends PathMatchingFilter {

    private Subject getSubject(ServletRequest request, ServletResponse response) {
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
    private boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
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

    private void sendChallenge(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write(errorMessage().toString());
    }

    private JSONObject errorMessage() throws JSONException {
        JSONObject object = new JSONObject();
        JSONObject meta = new JSONObject();
        meta.put("message", "Unauthorized");
        meta.put("success", false);
        meta.put("code", 401);
        object.put("meta", meta);
        return object;
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        boolean isAllow = isAccessAllowed(request, response, mappedValue);
        if (!isAllow) {
            sendChallenge(request, response);
        }
        return isAllow;
    }

}
