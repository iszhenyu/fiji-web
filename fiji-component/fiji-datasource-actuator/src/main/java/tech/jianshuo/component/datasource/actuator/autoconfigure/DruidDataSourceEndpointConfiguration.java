package tech.jianshuo.component.datasource.actuator.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tech.jianshuo.component.datasource.actuator.DruidDataSourceEndpoint;
import tech.jianshuo.component.datasource.actuator.DruidDataSourceMvcEndpoint;

/**
 * Druid Endpoint & Druid MvcEndpoint 自动配置，默认开启
 *
 * @author zhenyu
 */
@Configuration
@ConditionalOnProperty(prefix = "endpoints.druid", name = "enabled", havingValue = "true", matchIfMissing = true)
public class DruidDataSourceEndpointConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DruidDataSourceEndpoint druidDataSourceEndpoint() {
        return new DruidDataSourceEndpoint();
    }

    @Bean
    @ConditionalOnMissingBean
    public DruidDataSourceMvcEndpoint druidDataSourceMvcEndpoint(DruidDataSourceEndpoint druidDataSourceEndpoint) {
        return new DruidDataSourceMvcEndpoint(druidDataSourceEndpoint);
    }

}