package com.aliang.Controller;

import com.aliang.Common.CommonVo;
import com.aliang.client.UserFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/UserFeign")
public class UserByFeignController {

    @Resource
    private UserFeignClient client; // 注入Feign的客户端，以供使用

    @GetMapping("/queryById/{id}")
    public CommonVo queryById(@PathVariable("id") Long id) {
        CommonVo userCommonVo = client.queryById(id);
        return userCommonVo;
    }
}
