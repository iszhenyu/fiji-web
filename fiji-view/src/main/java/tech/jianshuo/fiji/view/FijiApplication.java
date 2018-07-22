package tech.jianshuo.fiji.view;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import tech.jianshuo.fiji.api.ApiApplication;
import tech.jianshuo.fiji.biz.BizApplication;
import tech.jianshuo.fiji.common.CommonApplication;
import tech.jianshuo.fiji.core.CoreApplication;
import tech.jianshuo.fiji.security.SecurityApplication;

/**
 * @author zhen.yu
 * @since 2018/7/20
 */
@SpringBootApplication
public class FijiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(
                        CommonApplication.class,
                        CoreApplication.class,
                        SecurityApplication.class,
                        BizApplication.class,
                        ApiApplication.class,
                        FijiApplication.class
                )
                .run(args);
    }

}