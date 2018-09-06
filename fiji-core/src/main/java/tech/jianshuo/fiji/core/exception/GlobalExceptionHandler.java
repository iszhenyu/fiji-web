package tech.jianshuo.fiji.core.exception;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import tech.jianshuo.fiji.core.vo.ResponseVo;

/**
 * @author zhen.yu
 * @since 2018/7/11
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(FijiException.class)
    public ResponseEntity<ResponseVo> handleFijiException(FijiException e) {
        ResponseVo vo = ResponseVo.fail(e.getShowMessage());
        return new ResponseEntity<>(vo, e.getStatus());
    }

//    @ResponseBody
//    @ExceptionHandler(FijiException.class)
//    public ResponseVo handleFijiException(FijiException e) {
//        return ResponseVo.fail(e.getShowMessage());
//    }

    /**
     * 400 - Bad Request
     */
    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    public ResponseVo handleValidationException(ValidationException e) {
        return ResponseVo.fail(e.getMessage());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseVo handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseVo.fail("could_not_read_json");
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseBody
//    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseVo handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResponseVo.fail("request_method_not_supported");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseBody
//    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseVo handleHttpMediaTypeNotSupportedException(Exception e) {
        return ResponseVo.fail("content_type_not_supported");
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({RuntimeException.class})
    public ResponseVo handleRuntimeException(RuntimeException e) {
        logger.error("系统内部出错", e);
        return ResponseVo.fail(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(Exception e, HttpServletRequest request) throws Exception {
        logger.error("请求：{} 异常", request.getRequestURI(), e);
        ModelAndView mav = new ModelAndView();
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
