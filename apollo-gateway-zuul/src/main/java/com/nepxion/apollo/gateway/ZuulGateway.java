package com.nepxion.apollo.gateway;

/**
 * <p>Title: Nepxion Apollo</p>
 * <p>Description: Nepxion Apollo For Spring Cloud</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringCloudApplication
@EnableZuulProxy
public class ZuulGateway {
    /** Refer to followings: 
    EnableZuulProxy->ZuulProxyConfiguration->ZuulConfiguration->ZuulFilterConfiguration->Map<String, ZuulFilter> filters
    with key to value for "accessFilter":ZuulAccessFilter instance
    @Configuration
    protected static class ZuulFilterConfiguration {

        @Autowired
        private Map<String, ZuulFilter> filters;

        @Bean
        public ZuulFilterInitializer zuulFilterInitializer() {
            return new ZuulFilterInitializer(this.filters);
        }

    }*/

    @Bean
    public ZuulAccessFilter accessFilter() {
        return new ZuulAccessFilter();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulGateway.class).web(true).run(args);
    }

    // http://localhost:7777/apollo-service/add?a=1&b=2&accessToken=token
    // http://localhost:7777/apollo-service/getUser?name=Zhangsan&accessToken=token
}