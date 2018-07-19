package tech.jianshuo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author zhen.yu
 * @since 2018/6/29
 */
@SpringBootApplication
public class FijiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FijiApplication.class, args);
    }

}
