package tech.jianshuo.component.datasource;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.FilterAdapter;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import tech.jianshuo.component.datasource.properties.DruidDataSourceProperties;

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

    }

    private void initFilters() {
        List<Filter> proxyFilters = super.getProxyFilters();
        List<FilterAdapter> adapters = druidFilters.getIfAvailable();
//        .stream()
//                .filter(Objects::nonNull)
//                .filter(filter -> !proxyFilters.contains(filter))
//                .forEach(proxyFilters::add);
    }
}
