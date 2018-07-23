package tech.jianshuo.fiji.core.property;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhen.yu
 * Created on 2018-07-23
 */
@Configuration
@ConfigurationProperties(prefix = "tech.jianshuo.druid")
@Data
@EqualsAndHashCode(callSuper = false)
@Order(-1)
public class DruidProperties {
    private String username;
    private String password;
    private String servletPath = "/druid/*";
    private Boolean resetEnable = false;
    private List<String> allowIps;
    private List<String> denyIps;
}
