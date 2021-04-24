package com.atguigu.springcloud.dao;



import com.atguigu.springcloud.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao
{

    // dao接口的方法，若形参是对象，则不需要@Param
    // 若形参是常规数据类型，则加上@Param方便与mapper.xml中对应

    //新建订单
    void create(Order order);

    //修改订单状态，从0改为1
    void update(@Param("userId") Long userId,@Param("status") Integer status);
}