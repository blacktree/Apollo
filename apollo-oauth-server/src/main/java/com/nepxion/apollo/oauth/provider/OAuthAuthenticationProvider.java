package com.nepxion.apollo.oauth.provider;

/**
 * <p>Title: Nepxion Apollo</p>
 * <p>Description: Nepxion Apollo For Spring Cloud</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.util.Collections;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class OAuthAuthenticationProvider implements AuthenticationProvider {
    private static final Logger LOG = LoggerFactory.getLogger(OAuthAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object principal = authentication.getPrincipal();
        Object credentials = authentication.getCredentials();

        LOG.info("Principal={}, Credentials={}", principal, credentials);

        // 伪代码，用户名和密码必须为admin/123，未来跟用户权限系统做接口
        if (!StringUtils.equals(principal.toString(), "admin") || !StringUtils.equals(credentials.toString(), "123")) {
            return null;
        }

        // 返回一个Token对象表示登陆成功
        return new UsernamePasswordAuthenticationToken(principal, credentials, Collections.<GrantedAuthority> emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}