package tech.jianshuo.fiji.core.orm;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author zhen.yu
 * Created on 2018-07-23
 */
@Data
@ConfigurationProperties(prefix = "tech.jianshuo.druid")
public class DruidProperties {

    private String username;
    private String password;
    private String servletPath = "/druid/*";
    private Boolean resetEnable = false;
    private List<String> allowIps;
    private List<String> denyIps;

}
