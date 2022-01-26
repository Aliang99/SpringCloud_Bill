package com.aliang.client;

import com.aliang.Common.CommonVo;
import com.aliang.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign客户端配置
 */
@FeignClient(value = "Bill-Service",fallback = UserFallBack.class,configuration = FeignConfiguration.class) // user-service为服务提供者的微服务名称
public interface UserFeignClient {

    /**
     * Feign会通过动态代理，生成实现类
     * 添加了@FeignClient注解的接口，它的所有抽象方法，完全采用SpringMVC的注解，Feign会根据注解，自动生成URL，并访问获取结果
     * getmapping中给的地址，前面必须要斜杠，因为需要拼接，少了斜杠，拼接出来的地址会无法访问
     */
    @GetMapping("/user/queryById/{id}") // feign对该请求地址做处理成http://user-service//user/queryById/id，十分优雅
    public CommonVo queryById(@PathVariable("id") Long id);
}
