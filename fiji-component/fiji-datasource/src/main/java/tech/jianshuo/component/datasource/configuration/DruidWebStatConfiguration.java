package tech.jianshuo.component.datasource.configuration;

import javax.servlet.Filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.WebStatFilter;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.component.datasource.DruidConstants;
import tech.jianshuo.component.datasource.DruidDataSourceProperties;

/**
 * 用于采集 Web 和 JDBC 关联监控的数据
 * <p>
 * https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_%E9%85%8D%E7%BD%AEWebStatFilter
 *
 * @author zhenyu
 * Created on 2018-10-24
 */
@Slf4j
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(Filter.class)
@ConditionalOnProperty(
        prefix = DruidConstants.DRUID_WEB_STAT_PREFIX,
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class DruidWebStatConfiguration {

    @Bean
    public FilterRegistrationBean druidWebStatFilter(DruidDataSourceProperties druidProperties) {
        log.info("druid web-stat-filter init...");
        DruidDataSourceProperties.DruidWebStatProperties properties = druidProperties.getWebStat();
        FilterRegistrationBean<WebStatFilter> registration = new FilterRegistrationBean<>();
        WebStatFilter filter = new WebStatFilter();
        registration.setFilter(filter);
        registration.addUrlPatterns(properties.getUrlPattern() != null ? properties.getUrlPattern() : "/*");
        // 需要排除一些不必要的url，比如*.js,/jslib/*等等
        registration.addInitParameter("exclusions", properties.getExclusions());
        // session统计功能
        registration.addInitParameter("sessionStatEnable", Boolean.toString(properties.isSessionStatEnable()));

        if (properties.getSessionStatMaxCount() != null) {
            // 缺省sessionStatMaxCount是1000个。你可以按需要进行配置
            registration.addInitParameter("sessionStatMaxCount", Integer.toString(properties.getSessionStatMaxCount()));
        }
        if (properties.getPrincipalSessionName() != null) {
            // 你可以配置principalSessionName，使得druid能够知道当前的session的用户是谁
            registration.addInitParameter("principalSessionName", properties.getPrincipalSessionName());
        }
        if (properties.getPrincipalCookieName() != null) {
            // 如果你的user信息保存在cookie中，你可以配置principalCookieName，使得druid知道当前的user是谁
            registration.addInitParameter("principalCookieName", properties.getPrincipalCookieName());
        }
        // 配置profileEnable能够监控单个url调用的sql列表
        registration.addInitParameter("profileEnable", Boolean.toString(properties.isProfileEnable()));
        return registration;
    }

}