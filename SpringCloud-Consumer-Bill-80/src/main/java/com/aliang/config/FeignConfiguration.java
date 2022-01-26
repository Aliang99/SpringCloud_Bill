package com.aliang.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign的日志配置类
 */
@Configuration
public class FeignConfiguration {

    /**
     * 控制日志记录的级别取值如下
     */
//    public enum Level {
//        NONE, //没有记录。
//        BASIC, //仅记录请求方法和 URL 以及响应状态代码和执行时间
//        HEADERS,//记录基本信息以及请求和响应标头。
//        FULL //记录请求和响应的标头、正文和元数据。
//    }
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
