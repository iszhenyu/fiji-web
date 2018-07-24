package tech.jianshuo.fiji.core.exception;

import org.springframework.http.HttpStatus;

/**
 * @author zhen.yu
 * @since 2017/7/10
 */
public class NonValidArgumentException extends FijiException {
    private static final long serialVersionUID = -8037864982038440596L;

    public NonValidArgumentException() {
    }

    public NonValidArgumentException(String message) {
        super(message);
    }

    public NonValidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonValidArgumentException(Throwable cause) {
        super(cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public LogLevel getLogLevel() {
        return LogLevel.INFO;
    }
}
