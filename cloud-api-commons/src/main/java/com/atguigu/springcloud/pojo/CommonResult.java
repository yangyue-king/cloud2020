package com.atguigu.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//封装对前端的显示，前端通用JSON实体块
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    //状态码
    private Integer code;
    //传递给前端的消息
    private String message;

    private  T data;

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}