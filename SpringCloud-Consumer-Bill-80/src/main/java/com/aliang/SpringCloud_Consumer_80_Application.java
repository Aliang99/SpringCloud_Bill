package com.aliang;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication//(exclude= {DataSourceAutoConfiguration.class}) //exclude 避免因导入的其它模块中有类需要链接数据库而报错
//@EnableDiscoveryClient //开启Eureka客户端发现
//@EnableCircuitBreaker // 开启熔断
//@EnableFeignClients //开启Feign功能
@EnableEurekaClient
@SpringCloudApplication
@EnableFeignClients //开启Feign功能
public class SpringCloud_Consumer_80_Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloud_Consumer_80_Application.class,args);
    }


    /**
     * 创建远程调用模板对象
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
