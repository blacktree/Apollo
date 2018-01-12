package com.nepxion.apollo.reference.ribbon;

/**
 * <p>Title: Nepxion Apollo</p>
 * <p>Description: Nepxion Apollo For Spring Cloud</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class EurekaReferenceRibbonService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${service.cluster.name}")
    private String serviceClusterName;

    @HystrixCommand(fallbackMethod = "getUserFallback")
    public String getUser() {
        return restTemplate.getForEntity("http://" + serviceClusterName + "/getUser?name=Zhangsan", String.class).getBody();
    }

    public String getUserFallback() {
        return "getUser fallback";
    }

    @HystrixCommand(fallbackMethod = "addFallback")
    public String add() {
        return restTemplate.getForEntity("http://" + serviceClusterName + "/add?a=10&b=20", String.class).getBody();
    }

    public Integer addFallback() {
        return -9999;
    }
}