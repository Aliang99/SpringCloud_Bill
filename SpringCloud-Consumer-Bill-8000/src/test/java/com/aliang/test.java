package com.aliang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCloud_Consumer_8000_Application.class)
public class test {

    @Autowired
    private RibbonLoadBalancerClient client;

    @Test
    public void test(){
        ServiceInstance choose = client.choose("Bill-Service");
        String host = choose.getHost();
        int port = choose.getPort();
        System.out.println("HostName:"+host+",Port:"+port);
    }

    @Test
    public void test2(){
        // 轮询100次
        for (int i = 0; i < 100; i++) {
            ServiceInstance instance = this.client.choose("Bill-Service");
            System.out.println(instance.getHost() + ":" + instance.getPort());
        }
    }
}
