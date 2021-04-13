package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.pojo.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

//@mapper也有repository的作用，注入bean
@Mapper
public interface PaymentDao {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id")Long id);
}
