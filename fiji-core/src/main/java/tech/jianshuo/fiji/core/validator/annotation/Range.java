package tech.jianshuo.fiji.core.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhen.yu
 * @since 2017/7/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Range {
    String message() default "";
    int min() default -1;
    int max() default -1;
}
