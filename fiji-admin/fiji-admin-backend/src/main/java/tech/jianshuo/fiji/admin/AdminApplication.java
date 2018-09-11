package tech.jianshuo.fiji.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author zhen.yu
 * Created on 2018-09-06
 */
@SpringBootApplication(scanBasePackages = "tech.jianshuo.fiji")
public class AdminApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(AdminApplication.class)
                .run(args);
    }
}
