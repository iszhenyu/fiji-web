package tech.jianshuo.component.datasource;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * DruidDataSource 的回调接口，可以在 DruidDataSource 初始化之前对其进行自定义
 */
public interface DruidDataSourceCustomizer {

    /**
     * 自定义 DruidDataSource
     *
     * @param druidDataSource druid 数据源
     */
    void customize(DruidDataSource druidDataSource);

}