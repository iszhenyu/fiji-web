package tech.jianshuo.fiji.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * @author zhen.yu
 * Created on 2018-07-23
 */
@Component
@MapperScan("tech.jianshuo.fiji.biz.dao.mapper")
public class MybatisConfig {
}
