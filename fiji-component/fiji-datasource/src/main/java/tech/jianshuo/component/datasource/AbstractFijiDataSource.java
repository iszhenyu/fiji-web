package tech.jianshuo.component.datasource;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.FilterAdapter;
import com.alibaba.druid.filter.config.ConfigFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import tech.jianshuo.component.datasource.properties.DruidDataSourceProperties;
import tech.jianshuo.component.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

/**
 * @author zhenyu
 * @date 2018-10-18
 */
@ConfigurationProperties(prefix = DruidConstants.DRUID_DATA_SOURCE_PREFIX)
public class AbstractFijiDataSource extends DruidDataSource {
    private static final long serialVersionUID = 2109786850351486762L;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private DruidDataSourceProperties druidDataSourceProperties;

    @Autowired
    private ObjectProvider<List<FilterAdapter>> druidFilters;

    @PostConstruct
    public void initDruidParentProperties() {
        initDataSourceProperties();
        initConfigFilterProperties();
        initFilters();
    }

    private void initDataSourceProperties() {
        if (StringUtils.isNotEmpty(dataSourceProperties.getDriverClassName())) {
            super.setDriverClassName(dataSourceProperties.getDriverClassName());
        }
        if (StringUtils.isNotEmpty(dataSourceProperties.getUrl())) {
            super.setUrl(dataSourceProperties.getUrl());
        }
        if (StringUtils.isNotEmpty(dataSourceProperties.getUsername())) {
            super.setUsername(dataSourceProperties.getUsername());
        }
        if (StringUtils.isNotEmpty(dataSourceProperties.getPassword())) {
            super.setPassword(dataSourceProperties.getPassword());
        }
    }

    private void initConfigFilterProperties() {
        DruidDataSourceProperties.DruidConfigFilterProperties configFilterProperties = druidDataSourceProperties.getConfig();
        if (configFilterProperties.isEnabled()) {
            StringBuilder builder = new StringBuilder();
            builder.append(ConfigFilter.CONFIG_DECRYPT).append("=").append("true").append(";");
            if (!StringUtils.isEmpty(configFilterProperties.getKey())) {
                builder.append(ConfigFilter.CONFIG_KEY).append("=").append(configFilterProperties.getKey());
                builder.append(";");
            }
            if (!StringUtils.isEmpty(configFilterProperties.getFile())) {
                builder.append(ConfigFilter.CONFIG_FILE).append("=").append(configFilterProperties.getFile());
            }
            super.setConnectionProperties(builder.toString());
        }
    }

    private void initFilters() {
        List<Filter> proxyFilters = super.getProxyFilters();
        List<FilterAdapter> adapters = druidFilters.getIfAvailable();
        if (CollectionUtils.isNotEmpty(adapters)) {
            adapters.stream()
                    .filter(Objects::nonNull)
                    .filter(filter -> !proxyFilters.contains(filter))
                    .forEach(proxyFilters::add);
        }

    }
}
