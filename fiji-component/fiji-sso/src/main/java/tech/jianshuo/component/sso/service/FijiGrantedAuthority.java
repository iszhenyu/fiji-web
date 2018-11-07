package tech.jianshuo.component.sso.service;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author zhenyu
 * Created on 2018-11-06
 */
public class FijiGrantedAuthority implements GrantedAuthority {
    private String authority;

    public FijiGrantedAuthority(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}