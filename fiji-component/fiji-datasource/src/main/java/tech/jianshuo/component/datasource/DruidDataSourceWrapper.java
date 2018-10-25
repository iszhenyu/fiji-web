package tech.jianshuo.component.datasource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.FilterAdapter;
import com.alibaba.druid.filter.config.ConfigFilter;
import com.alibaba.druid.filter.encoding.CharsetParameter;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author zhenyu
 * @date 2018-10-18
 */
@ConfigurationProperties(prefix = DruidConstants.DRUID_DATA_SOURCE_PREFIX)
public class DruidDataSourceWrapper extends DruidDataSource implements InitializingBean {
    private static final long serialVersionUID = 2109786850351486762L;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private DruidDataSourceProperties druidDataSourceProperties;

    @Autowired
    private ObjectProvider<List<FilterAdapter>> druidFilters;

    @Override
    public void afterPropertiesSet() throws Exception {
        initDataSourceProperties();
        initConnectionProperties();
        initFilters();
    }

    private void initDataSourceProperties() {
        if (super.getUsername() == null) {
            super.setUsername(dataSourceProperties.determineUsername());
        }
        if (super.getPassword() == null) {
            super.setPassword(dataSourceProperties.determinePassword());
        }
        if (super.getUrl() == null) {
            super.setUrl(dataSourceProperties.determineUrl());
        }
        if(super.getDriverClassName() == null){
            super.setDriverClassName(dataSourceProperties.getDriverClassName());
        }
    }

    private void initConnectionProperties() {
        StringBuilder builder = new StringBuilder();
        // 解析 config-filter 的配置
        DruidDataSourceProperties.DruidConfigFilterProperties configProperties = druidDataSourceProperties.getConfig();
        if (configProperties.isEnabled()) {
            builder.append(ConfigFilter.CONFIG_DECRYPT).append("=").append("true").append(";");
            if (!StringUtils.isEmpty(configProperties.getKey())) {
                builder.append(ConfigFilter.CONFIG_KEY).append("=").append(configProperties.getKey()).append(";");
            }
            if (!StringUtils.isEmpty(configProperties.getFile())) {
                builder.append(ConfigFilter.CONFIG_FILE).append("=").append(configProperties.getFile()).append(";");
            }
        }
        // 解析 encoding-filter 的配置
        DruidDataSourceProperties.DruidEncodingFilterProperties encodingProperties = druidDataSourceProperties.getEncoding();
        if (encodingProperties.isEnabled()) {
            if (!StringUtils.isEmpty(encodingProperties.getClientEncoding())) {
                builder.append(CharsetParameter.CLIENTENCODINGKEY).append("=").append(encodingProperties.getClientEncoding()).append(";");
            }
            if (!StringUtils.isEmpty(encodingProperties.getServerEncoding())) {
                builder.append(CharsetParameter.SERVERENCODINGKEY).append("=").append(encodingProperties.getServerEncoding()).append(";");
            }
        }
        if (builder.length() > 0) {
            super.setConnectionProperties(builder.toString());
        }
    }

    private void initFilters() {
        List<Filter> proxyFilters = super.getProxyFilters();
        druidFilters.getIfAvailable(ArrayList::new).stream()
                .filter(Objects::nonNull)
                .filter(filter -> !proxyFilters.contains(filter))
                .forEach(proxyFilters::add);
    }
}
