package tech.jianshuo.fiji.core.exception;

import org.springframework.http.HttpStatus;

/**
 * @author yuzhen
 * Created on 2018-07-15
 */
public class UnauthorizedException extends FijiException {

    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

    @Override
    public LogLevel getLogLevel() {
        return null;
    }

}
