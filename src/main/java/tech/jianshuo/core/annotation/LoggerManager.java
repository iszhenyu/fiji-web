package tech.jianshuo.core.annotation;

import java.lang.annotation.*;

/**
 * @author zhen.yu
 * @since 2018/7/11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManager {
    String desc();
}
