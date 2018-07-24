package tech.jianshuo.fiji.core.validator;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import tech.jianshuo.fiji.core.exception.NonValidArgumentException;

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
            throw new NonValidArgumentException(validation.message());
        }
        return value;
    }
}
