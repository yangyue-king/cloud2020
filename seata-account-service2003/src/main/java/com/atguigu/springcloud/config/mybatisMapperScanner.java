package com.atguigu.springcloud.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@MapperScan({"com.atguigu.springcloud.dao"})
public class mybatisMapperScanner {
}
