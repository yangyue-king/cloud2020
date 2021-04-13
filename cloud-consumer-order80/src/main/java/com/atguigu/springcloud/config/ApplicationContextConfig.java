package com.atguigu.springcloud.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {


    @Bean
    @LoadBalanced //赋予我们RestTemplate负载均衡能力
    //注销上面的注解则关闭 Ribbon 的客户端负载均衡，RestTemplate没有负载均衡能力，这样则不可以通过服务名访问了
    //这样可以采用自己的负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
