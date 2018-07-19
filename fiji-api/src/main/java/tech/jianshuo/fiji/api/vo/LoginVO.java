package tech.jianshuo.fiji.api.vo;

import tech.jianshuo.model.user.User;

/**
 * @author zhen.yu
 * @since 2017/6/15
 */
public class LoginVO {
    private String token;
    private User user;

    public LoginVO(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
