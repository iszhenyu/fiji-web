package tech.jianshuo.component.datasource.init;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceSchemaCreatedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

import com.alibaba.druid.pool.DruidDataSource;

import tech.jianshuo.component.util.CollectionUtils;

/**
 * Bean to handle {@link DataSource} initialization by running {@literal schema-*.sql} on
 * {@link InitializingBean#afterPropertiesSet()} and {@literal data-*.sql} SQL scripts on
 * a {@link DataSourceSchemaCreatedEvent}.
 *
 * @author Stephane Nicoll
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 */
public class DataSourceInitializerInvoker
        implements ApplicationListener<DataSourceSchemaCreatedEvent>, InitializingBean {

    private static final Log logger = LogFactory.getLog(DataSourceInitializerInvoker.class);

    private final List<DruidDataSource> dataSources;

    private final DataSourceProperties properties;

    private final ApplicationContext applicationContext;

    private DataSourceInitializer dataSourceInitializer;

    private boolean initialized;

    public DataSourceInitializerInvoker(ObjectProvider<List<DruidDataSource>> dataSourcesProvider,
                                        DataSourceProperties properties,
                                        ApplicationContext applicationContext) {
        this.dataSources = dataSourcesProvider.getIfAvailable(ArrayList::new);
        this.properties = properties;
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        ensureDataSourceInitializer();
        if (this.dataSourceInitializer != null) {
            boolean schemaCreated = this.dataSourceInitializer.createSchema();
            if (schemaCreated) {
                initialize(this.dataSourceInitializer);
            }
        }
    }

    private void initialize(DataSourceInitializer initializer) {
        try {
            initializer.getDataSources().stream()
                    .map(DataSourceSchemaCreatedEvent::new)
                    .forEach(this.applicationContext::publishEvent);
            // The listener might not be registered yet, so don't rely on it.
            if (!this.initialized) {
                initializer.initSchema();
                this.initialized = true;
            }
        } catch (IllegalStateException ex) {
            logger.warn("Could not send event to complete DataSource initialization ("
                    + ex.getMessage() + ")");
        }
    }

    @Override
    public void onApplicationEvent(DataSourceSchemaCreatedEvent event) {
        ensureDataSourceInitializer();
        if (!this.initialized && this.dataSourceInitializer != null) {
            this.dataSourceInitializer.initSchema();
            this.initialized = true;
        }
    }

    private void ensureDataSourceInitializer() {
        if (this.dataSourceInitializer == null) {
            List<DruidDataSource> dataSources = this.dataSources;
            if (CollectionUtils.isNotEmpty(dataSources)) {
                this.dataSourceInitializer = new DataSourceInitializer(dataSources, this.properties, this.applicationContext);
            }
        }
    }

}
