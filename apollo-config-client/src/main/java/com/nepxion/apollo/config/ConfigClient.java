package com.nepxion.apollo.config;

/**
 * <p>Title: Nepxion Apollo</p>
 * <p>Description: Nepxion Apollo For Spring Cloud</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ConfigClient {
    @Autowired
    public void setEnviroment(Environment env) {
        System.out.println("my-config.appName from env: " + env.getProperty("my-config.appName"));
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigClient.class).web(true).run(args);
    }
}