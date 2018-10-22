package tech.jianshuo.component.datasource.actuator;

import java.util.List;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.alibaba.druid.stat.DruidStatManagerFacade;

/**
 * 将 Druid 的信息暴露出来
 *
 * @author zhenyu
 */
@ConfigurationProperties(prefix = "endpoints.druid")
@Endpoint(id = "druid")
public class DruidDataSourceEndpoint {

    private static final DruidStatManagerFacade STAT_MANAGER = DruidStatManagerFacade.getInstance();

    @ReadOperation
    public List<Map<String, Object>> invoke() {
        return STAT_MANAGER.getDataSourceStatDataList();
    }

}