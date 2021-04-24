package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.pojo.CommonResult;
import com.atguigu.springcloud.pojo.Order;
import com.atguigu.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class OrderController{
    @Resource
    private OrderService orderService;


    //浏览器输入带请求参数的地址，请求参数为实体类属性，自动封装进对象
    @GetMapping("/order/create")
    public CommonResult create(Order order)
    {
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
