package tech.jianshuo.fiji.core.validator;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author zhen.yu
 * Created on 2018-07-25
 */
@Configuration
public class ValidatorConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new ArgumentNotEmptyResolver());
        argumentResolvers.add(new ArgumentNotNullResolver());
        argumentResolvers.add(new ArgumentMinResolver());
        argumentResolvers.add(new ArgumentMaxResolver());
        argumentResolvers.add(new ArgumentInRangeResolver());
    }
}