package com.atguigu.myselfLbRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyLBRule {

    @Bean
    public IRule myRule(){
        return new RandomRule();//定义为随机
    }
}
