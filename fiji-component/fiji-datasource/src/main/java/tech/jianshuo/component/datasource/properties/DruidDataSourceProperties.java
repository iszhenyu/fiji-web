package tech.jianshuo.component.datasource.properties;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import tech.jianshuo.component.datasource.DruidConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhenyu
 * @date 2018-10-18
 */
@Setter
@Getter
@ConfigurationProperties(prefix = DruidConstants.DRUID_DATA_SOURCE_PREFIX)
public class DruidDataSourceProperties {

    /** druid 数据源 */
    Map<String, DruidDataSource> dataSources = new HashMap<>();

    /** druid encoding-filter 配置 */
    @NestedConfigurationProperty
    DruidEncodingFilterProperties encoding = new DruidEncodingFilterProperties();

    /** druid config-filter 配置 */
    @NestedConfigurationProperty
    DruidConfigFilterProperties config = new DruidConfigFilterProperties();

    /** druid stat-view-servlet 配置 */
    @NestedConfigurationProperty
    DruidStatViewServletProperties statViewServlet = new DruidStatViewServletProperties();

    /** druid web-stat 配置 */
    @NestedConfigurationProperty
    DruidWebStatProperties webStat = new DruidWebStatProperties();

    /** druid aop-stat 配置 */
    @NestedConfigurationProperty
    DruidAopStatProperties aopStat = new DruidAopStatProperties();

    @Getter
    @Setter
    public static class DruidEncodingFilterProperties {
        /** 是否开启 druid encoding-filter，默认否 */
        private boolean enabled;
        /** 客户端编码 */
        private String clientEncoding;
        /** 服务端编码 */
        private String serverEncoding;
    }

    @Getter
    @Setter
    public static class DruidConfigFilterProperties {
        /** 是否开启 druid config-filter，默认否 */
        private boolean enabled;
        /** 密钥地址 */
        private String file;
        /** 加密密钥 */
        private String key;
    }

    @Getter
    @Setter
    public static class DruidStatViewServletProperties {
        /** 是否开启 druid 的数据统计页面，默认否 */
        private boolean enabled;
        /** servlet 的映射规则，默认访问 "http:/**xxx/druid/" */
        private String urlMappings = "/druid/*";
        /** druid 统计页面的登陆用户名 */
        private String loginUsername;
        /** druid 统计页面的登陆密码 */
        private String loginPassword;
        /** druid 统计页面的访问白名单，默认允许所有人访问 */
        private String allow;
        /** druid 统计页面的访问黑名单，优先于白名单 */
        private String deny;
        /** 是否允许清空统计数据，默认否 */
        private boolean resetEnable;
    }

    @Getter
    @Setter
    public static class DruidWebStatProperties {
        /** 是否开启 web-jdbc 监控，默认否 */
        private boolean enabled;
        /** 过滤器 url 的映射规则 */
        private String urlPatterns = "/*";
        /** 过滤器 url 的排除规则 */
        private String exclusions = "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*";
        /** 是否开启 session 统计，默认否 */
        private boolean sessionStatEnable;
        /** session 统计的最大值，默认值 1000 */
        private Integer sessionStatMaxCount = 1000;
        /** 配置当前 session 的用户 */
        private String principalSessionName;
        /** 配置当前 cookie 的用户 */
        private String principalCookieName;
        /** 是否开启监控单个 url 调用的 sql 列表，默认是 */
        private boolean profileEnable = true;
    }

    @Getter
    @Setter
    public static class DruidAopStatProperties {
        /** 是否开启基于 aop 的监控，默认否 */
        private boolean enabled;
        /** aop 拦截规则 */
        private String[] patterns;
    }
}
