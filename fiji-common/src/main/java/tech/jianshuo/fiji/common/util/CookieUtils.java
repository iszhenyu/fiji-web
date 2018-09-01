package tech.jianshuo.fiji.common.util;

import java.util.Objects;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhen.yu
 * @since 2017/8/11
 */
public class CookieUtils {
    private static Logger logger = LoggerFactory.getLogger(CookieUtils.class);

    public static void addCookie(HttpServletResponse response, String name,
                                 String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static String getCookieByName(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies || cookies.length == 0) {
            return null;
        }
        return Stream.of(cookies)
                .filter(c -> StringUtils.isNoneBlank(c.getName()) && Objects.equals(c.getName(), name))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }

    public static boolean clearCookie(HttpServletRequest request, HttpServletResponse response,
                                      String name) {
        return clearCookie(request, response, name, null);
    }

    public static boolean clearCookie(HttpServletRequest request, HttpServletResponse response,
                                      String name, String domain) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookies.length == 0) {
            return false;
        }
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/"); // 根据你创建cookie的路径进行填写
        if (StringUtils.isNoneBlank(domain)) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
        return true;
    }

}
