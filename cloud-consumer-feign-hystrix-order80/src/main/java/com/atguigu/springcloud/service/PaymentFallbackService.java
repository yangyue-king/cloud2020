package com.atguigu.springcloud.service;


import org.springframework.stereotype.Component;


// 定义一个类实现 Feign微服务接口，则这个类可当作该微服务的 fallback
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK , (┬＿┬)";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut , (┬＿┬)";

    }
}
