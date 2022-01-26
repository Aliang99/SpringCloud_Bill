package com.aliang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloud_EurekaServer_7003_Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloud_EurekaServer_7003_Application.class, args);
    }
}
