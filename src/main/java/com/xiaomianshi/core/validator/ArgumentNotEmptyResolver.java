package com.xiaomianshi.core.validator;

import com.xiaomianshi.core.validator.annotation.NotEmpty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author zhen.yu
 * @since 2017/7/10
 */
public class ArgumentNotEmptyResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(NotEmpty.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String parameter = methodParameter.getParameterName();
        String value = webRequest.getParameter(parameter);
        if (StringUtils.isEmpty(value)) {
            NotEmpty validation = methodParameter.getParameterAnnotation(NotEmpty.class);
            throw new IllegalArgumentException(validation.message());
        }
        return value;
    }
}
