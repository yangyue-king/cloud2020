package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


// OpenFeign 的使用
// 高仿本地service接口 + @FeignClient 使得调用远程微服务像本地一样简单
// 相当于 CLOUD-CONSUMER-HYSTRIX-ORDER80 微服务的controller 接口


// 定义一个类实现 Feign微服务接口，则这个类可当作该微服务的 fallback
// 使能 feign 的 hystrix ，这里的fallback才能生效
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
