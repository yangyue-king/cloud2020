package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

//import com.atguigu.springcloud.entities.*;
import com.atguigu.springcloud.myhandler.CustomerBlockHandler;
import com.atguigu.springcloud.pojo.CommonResult;
import com.atguigu.springcloud.pojo.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RateLimitController
{
    @GetMapping("/byResource")
    // 在 sentinel-dashboard 上可根据资源名称限流
    // 资源名称为 byResource
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource()
    {
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockException exception)
    {
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }


    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    // 指定 blockHandlerClass 类下的哪个 blockHandler 方法兜底
    public CommonResult customerBlockHandler()
    {
        return new CommonResult(200,"按客戶自定义",new Payment(2020L,"serial003"));
    }


    // 流控配置持久化测试，这里的配置来自于持久化的nacos，默认来自于非持久化的sentinel
    @GetMapping("/retaLimit/byUrl")
    @SentinelResource(value = "retaLimit-byUrl")
    public String customerBlockHandler2()
    {
        return "wo shi ni die";
    }

}
