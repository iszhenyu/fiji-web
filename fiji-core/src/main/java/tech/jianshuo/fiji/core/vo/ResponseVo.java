package tech.jianshuo.fiji.core.vo;

/**
 * @author zhen.yu
 * @since 2017/7/7
 */
public class ResponseVo {
    private static final String OK = "ok";
    private static final String ERROR = "error";

    private Meta meta;
    private Object data;

    public static ResponseVo success() {
        return new ResponseVo().fillSuccess();
    }

    public static ResponseVo success(Object data) {
        return new ResponseVo().fillSuccess(data);
    }

    public static ResponseVo fail() {
        return new ResponseVo().fillFailure();
    }

    public static ResponseVo fail(String message) {
        return new ResponseVo().fillFailure(message);
    }

    public static ResponseVo fail(String message, int errorCode) {
        return new ResponseVo().fillFailure(message, errorCode);
    }

    public ResponseVo fillSuccess() {
        this.meta = new Meta(true, OK);
        return this;
    }

    public ResponseVo fillSuccess(Object successData) {
        this.meta = new Meta(true, OK);
        this.data = successData;
        return this;
    }

    public ResponseVo fillFailure() {
        this.meta = new Meta(false, ERROR);
        return this;
    }

    public ResponseVo fillFailure(String message) {
        this.meta = new Meta(false, message);
        return this;
    }

    public ResponseVo fillFailure(String message, int errorCode) {
        this.meta = new Meta(false, message, errorCode);
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }

    public class Meta {

        private int code;
        private boolean success;
        private String message;

        public Meta(boolean success) {
            this.success = success;
        }

        public Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public Meta(boolean success, String message, int code) {
            this.success = success;
            this.message = message;
            this.code = code;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
