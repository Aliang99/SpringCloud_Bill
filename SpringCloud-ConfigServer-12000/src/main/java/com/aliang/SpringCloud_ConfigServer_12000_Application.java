package com.aliang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置文件管理模块
 * 从gitee中获取配置文件，达成热修改目的
 */
@SpringBootApplication
@EnableConfigServer  //开启配置服务
public class SpringCloud_ConfigServer_12000_Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloud_ConfigServer_12000_Application.class,args);
    }
}
