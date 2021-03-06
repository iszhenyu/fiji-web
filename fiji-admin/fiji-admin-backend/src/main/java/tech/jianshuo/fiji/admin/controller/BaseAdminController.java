package tech.jianshuo.fiji.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import tech.jianshuo.fiji.core.exception.ValidationException;

/**
 * @author zhen.yu
 * Created on 2018-09-06
 */
public abstract class BaseAdminController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void validateForm(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw new ValidationException(errors.get(0).getDefaultMessage());
        }
    }
}
