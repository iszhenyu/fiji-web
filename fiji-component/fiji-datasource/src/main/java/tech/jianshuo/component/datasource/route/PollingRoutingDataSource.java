package tech.jianshuo.component.datasource.route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhenyu
 * Created on 2018-10-25
 */
@Slf4j
public class PollingRoutingDataSource extends AbstractRoutingDataSource {

    private final AtomicInteger count = new AtomicInteger();
    private final Map<String, DataSource> targetDataSources;

    public PollingRoutingDataSource(Map<String, DataSource> targetDataSources) {
        this.targetDataSources = targetDataSources;
    }

    public Map<String, DataSource> getTargetDataSources() {
        return targetDataSources;
    }

    /**
     * 基于轮询算法指定实际的数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        int i = count.incrementAndGet();
        List<String> list = new ArrayList<>(targetDataSources.keySet());
        String dataSource = list.get(i % list.size());
        log.debug(">>>>>> 当前数据库: {} <<<<<<", dataSource);
        return dataSource;
    }

    @Override
    public void afterPropertiesSet() {
        super.setTargetDataSources(new HashMap<>(targetDataSources));
        super.afterPropertiesSet();
    }
}
