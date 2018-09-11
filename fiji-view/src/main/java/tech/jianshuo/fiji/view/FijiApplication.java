package tech.jianshuo.fiji.view;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author zhen.yu
 * @since 2018/7/20
 */
@SpringBootApplication(scanBasePackages = "tech.jianshuo.fiji")
public class FijiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(FijiApplication.class)
                .run(args);
    }

}