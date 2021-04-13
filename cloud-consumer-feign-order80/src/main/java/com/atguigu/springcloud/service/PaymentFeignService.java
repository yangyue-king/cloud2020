package com.atguigu.springcloud.service;


import com.atguigu.springcloud.pojo.CommonResult;
import com.atguigu.springcloud.pojo.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



//OpenFeign的服务调用和负载均衡
//OpenFeign的使用是  定义一个 微服务调用接口（PaymentFeignService）加上 @FeignClient

//这个module采用的是 OpenFeign 而不是 RestTemplate + Ribbon
//本接口就是远程微服务暴露的所有 http接口


@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") //指定本接口对应的微服务
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);

    //上面两行代码须和微服务中的 接口方法一样

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout();

    //接口中的属性和方法不加权限修饰符。属性默认为 public static final为常量
    // 方法默认为 public abstract
}

