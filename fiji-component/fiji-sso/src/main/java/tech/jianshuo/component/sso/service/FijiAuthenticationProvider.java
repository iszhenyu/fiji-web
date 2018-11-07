package tech.jianshuo.component.sso.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author zhenyu
 * Created on 2018-11-06
 */
public class FijiAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 认证逻辑
        if (name.equals("admin") && password.equals("123456")) {

            // 这里设置权限和角色
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add( new FijiGrantedAuthority("ROLE_ADMIN") );
            authorities.add( new FijiGrantedAuthority("AUTH_WRITE") );
            // 生成令牌
            return new UsernamePasswordAuthenticationToken(name, password, authorities);
        }else {
            throw new BadCredentialsException("密码错误~");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
