package tech.jianshuo.fiji.core.property;

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
@ConfigurationProperties(prefix = "spring.redis")
@Data
@EqualsAndHashCode(callSuper = false)
@Order(-1)
public class RedisProperties {



}
