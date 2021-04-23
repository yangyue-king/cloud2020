package com.atguigu.springcloud.fallback;

import com.atguigu.springcloud.pojo.CommonResult;
import com.atguigu.springcloud.pojo.Payment;
import org.springframework.web.bind.annotation.PathVariable;


// 用这个类统一放置 controller的运行时异常的 fallback，防止代码膨胀
public class FallbackClass {
    public static CommonResult handlerFallback(@PathVariable Long id, Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"兜底异常handlerFallback,exception内容  "+e.getMessage(),payment);
    }
}
