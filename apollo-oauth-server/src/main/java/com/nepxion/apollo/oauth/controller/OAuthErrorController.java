package com.nepxion.apollo.oauth.controller;

/**
 * <p>Title: Nepxion Apollo</p>
 * <p>Description: Nepxion Apollo For Spring Cloud</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 自定义的认证错误页
 */
@Controller
@SessionAttributes("authorizationRequest")
public class OAuthErrorController {
    private static final Logger LOG = LoggerFactory.getLogger(OAuthErrorController.class);

    @RequestMapping("/oauth/error")
    public String error(@RequestParam Map<String, String> parameters) {
        String uri = parameters.get("redirect_uri");

        LOG.info("重定向: {}", uri);

        return "redirect:" + uri + "?error=1";
    }
}