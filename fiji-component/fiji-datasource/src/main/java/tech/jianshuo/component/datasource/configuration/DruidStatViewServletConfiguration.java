package tech.jianshuo.component.datasource.configuration;

import javax.servlet.Servlet;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.alibaba.druid.support.http.StatViewServlet;

import lombok.extern.slf4j.Slf4j;

import tech.jianshuo.component.datasource.DruidConstants;
import tech.jianshuo.component.datasource.DruidDataSourceProperties;

/**
 * Druid 提供了一个 StatViewServlet 用于展示 Druid 的统计信息
 * 这个 StatViewServlet 的用途包括：
 *   1. 提供监控信息展示的 HTML 页面
 *   2. 提供监控信息的 JSON API
 *
 * @author zhenyu
 */
@Slf4j
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(Servlet.class)
@ConditionalOnProperty(
        prefix = DruidConstants.DRUID_STAT_VIEW_SERVLET_PREFIX,
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class DruidStatViewServletConfiguration {

    @Bean
    public ServletRegistrationBean druidStatViewServlet(DruidDataSourceProperties druidProperties) {
        log.info("druid stat-view-servlet init...");
        DruidDataSourceProperties.DruidStatViewServletProperties properties = druidProperties.getStatViewServlet();
        ServletRegistrationBean<StatViewServlet> registration = new ServletRegistrationBean<>(new StatViewServlet());
        registration.addUrlMappings(properties.getUrlPattern());
        if (properties.getLoginUsername() != null) {
            registration.addInitParameter("loginUsername", properties.getLoginUsername());
        }
        if (properties.getLoginPassword() != null) {
            registration.addInitParameter("loginPassword", properties.getLoginPassword());
        }
        if (properties.getAllow() != null) {
            registration.addInitParameter("allow", properties.getAllow());
        }
        if (properties.getDeny() != null) {
            registration.addInitParameter("deny", properties.getDeny());
        }
        registration.addInitParameter("resetEnable", Boolean.toString(properties.isResetEnable()));
        return registration;
    }

}