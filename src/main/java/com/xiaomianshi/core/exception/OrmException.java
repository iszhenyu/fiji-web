package com.xiaomianshi.core.exception;

import org.springframework.http.HttpStatus;

/**
 * @author yuzhen <yuzhen@kuaishou.com>
 * Created on 2018-07-15
 */
public class OrmException extends FijiException {
    public OrmException() {
    }

    public OrmException(String message) {
        super(message);
    }

    public OrmException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrmException(Throwable cause) {
        super(cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public LogLevel getLogLevel() {
        return LogLevel.ERROR;
    }
}
