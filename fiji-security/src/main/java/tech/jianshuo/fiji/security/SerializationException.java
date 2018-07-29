package tech.jianshuo.fiji.security;

import org.springframework.http.HttpStatus;

import tech.jianshuo.fiji.core.exception.FijiException;
import tech.jianshuo.fiji.core.exception.LogLevel;

/**
 * @author zhen.yu
 * Created on 2018-07-27
 */
public class SerializationException extends FijiException {

    public SerializationException() {
    }

    public SerializationException(String message) {
        super(message);
    }

    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerializationException(Throwable cause) {
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
