package tech.jianshuo.component.datasource.configuration;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;

import lombok.extern.slf4j.Slf4j;
import tech.jianshuo.component.datasource.DruidConstants;
import tech.jianshuo.component.datasource.properties.DruidDataSourceProperties;

/**
 * 用于采集 Spring 和 JDBC 关联监控的数据
 * @author zhenyu
 * Created on 2018-10-24
 */
@Slf4j
@Configuration
@ConditionalOnClass(Advice.class)
@ConditionalOnProperty(
        prefix = DruidConstants.DRUID_AOP_STAT_PREFIX,
        name = "enabled",
        havingValue = "true"
)
public class DruidAopStatConfiguration {

    @Bean
    public Advice advice() {
        return new DruidStatInterceptor();
    }

    @Bean
    public Advisor advisor(DruidDataSourceProperties druidProperties) {
        DruidDataSourceProperties.DruidAopStatProperties properties = druidProperties.getAopStat();
        return new RegexpMethodPointcutAdvisor(properties.getPatterns(), advice());
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator druidAdvisorAutoProxyCreator() {
        log.info("druid aop-stat init...");
        DefaultAdvisorAutoProxyCreator druidStatProxyCreator = new DefaultAdvisorAutoProxyCreator();
        druidStatProxyCreator.setProxyTargetClass(true);
        return druidStatProxyCreator;
    }

}
