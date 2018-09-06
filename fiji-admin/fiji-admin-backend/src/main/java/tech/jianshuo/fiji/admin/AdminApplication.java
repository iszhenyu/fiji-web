package tech.jianshuo.fiji.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import tech.jianshuo.fiji.biz.BizApplication;
import tech.jianshuo.fiji.common.CommonApplication;
import tech.jianshuo.fiji.core.CoreApplication;
import tech.jianshuo.fiji.security.SecurityApplication;

/**
 * @author zhen.yu
 * Created on 2018-09-06
 */
@SpringBootApplication
public class AdminApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(
                        CommonApplication.class,
                        CoreApplication.class,
                        SecurityApplication.class,
                        BizApplication.class,
                        AdminApplication.class
                )
                .run(args);
    }
}
