package tech.jianshuo.fiji.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author zhenyu
 * @date 2018-10-09
 */
@SpringBootApplication(scanBasePackages = "tech.jianshuo.fiji")
public class ApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(ApiApplication.class)
                .run(args);
    }
}
