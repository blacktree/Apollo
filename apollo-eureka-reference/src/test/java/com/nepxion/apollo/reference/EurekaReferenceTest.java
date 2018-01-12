package com.nepxion.apollo.reference;

/**
 * <p>Title: Nepxion Apollo</p>
 * <p>Description: Nepxion Apollo For Spring Cloud</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EurekaReferenceTest {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaReferenceTest.class).web(true).run(args);
    }
}

@RestController
class Controller {
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String home() {
        return service.go();
    }
}

@Component
class Service {
    @Value("${app.baseUrl:http://example.org}")
    private String base;

    /*private final RestTemplate restTemplate;

    public Service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }*/
    
    @Autowired
    private RestTemplate restTemplate;

    public String go() {
        return restTemplate.getForEntity(base + "/resource", String.class).getBody();
    }
}