package tech.jianshuo.component.datasource.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import tech.jianshuo.component.datasource.DruidConstants;

/**
 * @author zhenyu
 * @date 2018-10-18
 */
@Setter
@Getter
@ConfigurationProperties(prefix = DruidConstants.DRUID_DATA_SOURCE_PREFIX)
public class DruidDataSourceProperties {


}
