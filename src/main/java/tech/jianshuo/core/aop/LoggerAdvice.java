package tech.jianshuo.core.aop;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tech.jianshuo.core.annotation.LoggerManager;

/**
 * @author zhen.yu
 * @since 2018/7/12.
 *
 */
@Aspect
@Service
public class LoggerAdvice {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Before("within(tech.jianshuo..*) && @annotation(loggerManage)")
    public void addBeforeLogger(JoinPoint joinPoint, LoggerManager loggerManage) {
        logger.info("执行 " + loggerManage.desc() + " 开始");
        logger.info(joinPoint.getSignature().toString());
        logger.info(parseParam(joinPoint.getArgs()));
    }

    @AfterReturning("within(tech.jianshuo..*) && @annotation(loggerManage)")
    public void addAfterReturningLogger(JoinPoint joinPoint, LoggerManager loggerManage) {
        logger.info("执行 " + loggerManage.desc() + " 结束");
    }

    @AfterThrowing(pointcut = "within(tech.jianshuo..*) && @annotation(loggerManage)", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, LoggerManager loggerManage, Exception ex) {
        logger.error("执行 " + loggerManage.desc() + " 异常", ex);
    }

    private String parseParam(Object[] parames) {
        if (null == parames || parames.length <= 0 || parames.length > 1024) {
            return "";
        }
        StringBuilder param = new StringBuilder("传入参数[{}] ");
        for (Object obj : parames) {
            param.append(ToStringBuilder.reflectionToString(obj)).append("  ");
        }
        return param.toString();
    }


}
