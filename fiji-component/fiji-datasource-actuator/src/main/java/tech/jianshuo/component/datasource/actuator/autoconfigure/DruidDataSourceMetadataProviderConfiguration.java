package tech.jianshuo.component.datasource.actuator.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

import tech.jianshuo.component.datasource.actuator.DruidDataSourcePoolMetadata;

/**
 * Druid Metadata 自动配置，适用于 Metrics，默认开启
 *
 * @author zhenyu
 */
@Configuration
@ConditionalOnClass(DruidDataSource.class)
public class DruidDataSourceMetadataProviderConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DataSourcePoolMetadataProvider dataSourcePoolMetadataProvider() {
        return (dataSource) -> {
            if (dataSource instanceof DruidDataSource) {
                return new DruidDataSourcePoolMetadata((DruidDataSource) dataSource);
            }
            return null;
        };
    }

}