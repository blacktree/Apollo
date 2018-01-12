package com.nepxion.apollo.oauth.config;

/**
 * <p>Title: Nepxion Apollo</p>
 * <p>Description: Nepxion Apollo For Spring Cloud</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 配置用户信息，以及受保护路径、允许访问路径
 */
@Configuration
public class OAuthWebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(OAuthWebSecurityConfig.class);

    @Autowired
    public DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        /*http.authorizeRequests()
                .antMatchers("/login.html", "/login/failure").permitAll()
                .anyRequest().hasRole("USER")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/login?authorization_error=true")
                .and()
                .csrf()
                .disable()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.jsp")
                .and()
                .formLogin()
                .loginPage("/login.html")//登陆页面  
                .loginProcessingUrl("/login")//登陆处理路径  
                .usernameParameter("username")//登陆用户名参数  
                .passwordParameter("password")//登陆密码参数  
                .defaultSuccessUrl("/login/success")//登陆成功路径  
                .failureUrl("/login/failure");//登陆失败路径*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favor.ico");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);

        /*auth.inMemoryAuthentication()
                .withUser("zhangsan")
                .password("zhangsan")
                .authorities("reader")
                .and()
                .withUser("lisi")
                .password("lisi")
                .authorities("reader", "writer");

        UserDetails userDetails = userDetailsService().loadUserByUsername("zhangsan");
        LOG.info("Password={}", userDetails.getPassword());

        auth.jdbcAuthentication().dataSource(dataSource);
        UserDetails userDetails = userDetailsService().loadUserByUsername("zhangsan");

        LOG.info("Password={}", userDetails.getPassword());*/
    }
}