package com.atguigu.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order
{
    // 订单ID，主键
    private Long id;

    // 用户ID
    private Long userId;

    // 产品ID
    private Long productId;

    // 买的产品数
    private Integer count;

    // 花了多少钱
    private BigDecimal money;

    //订单状态：0：创建中；1：已完结
    private Integer status;
}

