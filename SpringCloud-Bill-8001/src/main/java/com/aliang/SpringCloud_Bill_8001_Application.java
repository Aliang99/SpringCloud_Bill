package com.aliang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.aliang.mapper")
@EnableEurekaClient
@EnableDiscoveryClient
public class SpringCloud_Bill_8001_Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloud_Bill_8001_Application.class, args);
    }
}
