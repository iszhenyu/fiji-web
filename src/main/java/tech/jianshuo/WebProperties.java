package tech.jianshuo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhen.yu
 * @since 2018/6/30
 */
@Component
public class WebProperties {

    @Value("${com.jianshuo.web.name}")
    private String webName;

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }
}
