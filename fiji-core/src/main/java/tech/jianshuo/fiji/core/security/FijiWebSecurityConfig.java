package tech.jianshuo.fiji.core.security;

import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author zhenyu
 * Created on 2018-11-03
 * 这里不需要添加@EnableWebSecurity注解，{@link WebSecurityEnablerConfiguration} 已经实现
 */
@Configuration
//@EnableWebSecurity
public class FijiWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService fijiUserDetailsService() {
        return new FijiUserDetailsService();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(fijiUserDetailsService());
    }

}
