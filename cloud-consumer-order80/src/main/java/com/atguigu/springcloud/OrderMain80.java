package com.atguigu.springcloud;

import com.atguigu.myselfLbRule.MyLBRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@EnableEurekaClient
@SpringBootApplication
//替换负载均衡策略，由默认轮询改成随机，我们自己配了个随机的bean
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyLBRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }

}



