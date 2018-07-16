package com.xiaomianshi.core.exception;

import org.springframework.http.HttpStatus;

/**
 * @author yuzhen <yuzhen@kuaishou.com>
 * Created on 2018-07-15
 */
public class NotFoundException extends FijiException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public LogLevel getLogLevel() {
        return null;
    }
}
