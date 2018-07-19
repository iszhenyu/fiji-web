package tech.jianshuo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import tech.jianshuo.core.exception.ValidationException;

/**
 * @author zhen.yu
 * @since 2018/7/10
 */
public class FijiController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void validateForm(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw new ValidationException(errors.get(0).getDefaultMessage());
        }
    }

}
