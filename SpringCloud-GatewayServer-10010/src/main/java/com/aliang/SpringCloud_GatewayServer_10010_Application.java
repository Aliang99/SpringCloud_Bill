package com.aliang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient // 开启eureka的服务发现
public class SpringCloud_GatewayServer_10010_Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloud_GatewayServer_10010_Application.class, args);
    }
}
