package com.aliang.client;

import com.aliang.Common.CommonVo;
import org.springframework.stereotype.Component;

/**
 * 编写 Feign客户端的实现类，用于对发生熔断的请求做相应的处理
 */
@Component
public class UserFallBack implements UserFeignClient{
    /**
     * 对 queryById这个请求地址做熔断处理，请求超时时，调用以下的方法，响应客户端
     * @param id
     * @return
     */
    @Override
    public CommonVo queryById(Long id) {
        return new CommonVo(400,"抱歉，联通网络提醒您，您请求的服务暂时联不通，请稍后再试！！！",null);
    }
}
