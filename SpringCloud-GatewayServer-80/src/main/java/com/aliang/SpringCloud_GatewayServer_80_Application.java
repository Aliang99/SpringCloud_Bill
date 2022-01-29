package com.aliang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 开启eureka的服务发现
public class SpringCloud_GatewayServer_80_Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloud_GatewayServer_80_Application.class, args);
    }
}
