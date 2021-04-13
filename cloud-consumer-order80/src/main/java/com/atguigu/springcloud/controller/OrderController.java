package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.loadbalance.LoadBalance;
import com.atguigu.springcloud.pojo.CommonResult;
import com.atguigu.springcloud.pojo.Payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    //不用注册中心Eureka可以直接把服务地址写死，RestTemplate直接访问即可
    //public static final String PAYMENT_URL="http://localhost:8001";


    //集群版的Eureka是通过 EurekaServer 获取服务地址的，所以地址写服务名称
    //由 RestTemplate 负载均衡（轮询）决定实际服务地址
    //没有实际地址而是服务名称的话，RestTemplate访问的是 EurekaServer
    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalance loadBalancer;

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    //要区分 getFoObject()和 getForEntity()的区别
    //getFoObject()只获取响应的响应体，并转换成CommonResult类别的JSON

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    //getForEntity()获取整个响应，包括响应头，响应体，响应状态码
    // entity.getBody()可以获得响应体，和getFoObject()获得的JSON一样

    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            //log.info(entity.getStatusCode()+"\t"+entity);
            //相当于 getFoObject()
            return entity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败");
        }
    }

    //自己实现 轮询 负载均衡测试接口，访问这个接口时使用的不是 Ribbon的 负载均衡
    //相当于用我们自己的规则制定服务器地址
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

}
