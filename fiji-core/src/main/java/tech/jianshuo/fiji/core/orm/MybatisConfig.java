package tech.jianshuo.fiji.core.orm;

import org.springframework.stereotype.Component;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhen.yu
 * Created on 2018-07-23
 */
@Component
@MapperScan("tech.jianshuo.fiji.biz.persistence.mapper")
public class MybatisConfig {
}
