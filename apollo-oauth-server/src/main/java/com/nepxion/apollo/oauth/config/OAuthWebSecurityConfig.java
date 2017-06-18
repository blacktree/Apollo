package com.nepxion.apollo.oauth.config;

/**
 * <p>Title: Nepxion Apollo</p>
 * <p>Description: Nepxion Apollo For Spring Cloud</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
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
import org.springframework.security.core.userdetails.UserDetails;

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
        LOG.info("Password={}", userDetails.getPassword());*/

        auth.jdbcAuthentication().dataSource(dataSource);
        UserDetails userDetails = userDetailsService().loadUserByUsername("zhangsan");

        LOG.info("Password={}", userDetails.getPassword());
    }
}