package tech.jianshuo.fiji.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author zhen.yu
 * @since 2018/7/20
 */
@SpringBootApplication
public class FijiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FijiApplication.class, args);
    }

}