package tech.jianshuo.component.datasource;

import com.alibaba.druid.support.http.StatViewServlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import tech.jianshuo.component.datasource.properties.DruidDataSourceProperties;

import javax.servlet.Servlet;

import static tech.jianshuo.component.datasource.DruidConstants.DRUID_STAT_VIEW_SERVLET_PREFIX;

/**
 * Druid Servlet 配置
 *
 * @author zhenyu
 */
@Slf4j
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(Servlet.class)
@ConditionalOnProperty(prefix = DRUID_STAT_VIEW_SERVLET_PREFIX, name = "enabled", havingValue = "true")
public class DruidServletConfiguration {

    /**
     * Druid 提供了一个 StatViewServlet 用于展示 Druid 的统计信息
     * 这个 StatViewServlet 的用途包括：
     *   1. 提供监控信息展示的 HTML 页面
     *   2. 提供监控信息的 JSON API
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet(DruidDataSourceProperties druidProperties) {
        log.info("druid stat-view-servlet init...");
        DruidDataSourceProperties.DruidStatViewServletProperties properties = druidProperties.getStatViewServlet();
        ServletRegistrationBean<StatViewServlet> registration = new ServletRegistrationBean<>(new StatViewServlet());
        registration.addUrlMappings(properties.getUrlMappings());
        if (!StringUtils.isEmpty(properties.getLoginUsername())) {
            registration.addInitParameter("loginUsername", properties.getLoginUsername());
        }
        if (!StringUtils.isEmpty(properties.getLoginPassword())) {
            registration.addInitParameter("loginPassword", properties.getLoginPassword());
        }
        if (!StringUtils.isEmpty(properties.getAllow())) {
            registration.addInitParameter("allow", properties.getAllow());
        }
        if (!StringUtils.isEmpty(properties.getDeny())) {
            registration.addInitParameter("deny", properties.getDeny());
        }
        registration.addInitParameter("resetEnable", Boolean.toString(properties.isResetEnable()));
        return registration;
    }

}