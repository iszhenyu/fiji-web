package tech.jianshuo.component.datasource.actuator.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * Druid Actuator 自动配置，默认开启
 *
 * @author zhenyu
 */
@Configuration
@ConditionalOnClass(DruidDataSource.class)
@ConditionalOnProperty(prefix = "management.druid", name = "enabled", havingValue = "true", matchIfMissing = true)
@Import({DruidDataSourceEndpointConfiguration.class, DruidDataSourceMetadataProviderConfiguration.class})
public class DruidDataSourceActuatorAutoConfiguration {

}