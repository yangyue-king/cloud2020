package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderHystrixController {

    // Feign 服务接口
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){


        // openFeign远程调用微服务
        return paymentHystrixService.paymentInfo_OK(id);
    }

    // 本服务会等待 Feign 调用其他服务，若其他服务超时或者 本服务自己报错，
    // 则会按照hystrix的定制规则进行服务降级
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOut_Fallback_Handler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        // int a=10/0; 出错也走 fallback
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }


    // 服务消费者做的服务降级 fallback 措施
    // 再controller 接口方法上做服务降级
    public String paymentInfo_TimeOut_Fallback_Handler(Integer id){
        return "我是消费着80，8001繁忙或者出错，请稍后再试";
    }
}
