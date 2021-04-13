package com.atguigu.springcloud.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;


// Gateway自定义全局过滤器，需实现 GlobalFilter，Ordered接口
// Gateway一般做全局日志记录，统一用户权鉴，故我们在网关只需配置全局过滤器
// ServerWebExchange命名为服务网络交换器，存放着重要的请求-响应属性、请求实例和响应实例等等，有点像Context的角色
@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("============come in MyLogGatewayFilter: "+new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if(uname==null){
            log.info("============用户名为努力了，非法用户");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }

        // 可以通过
        return chain.filter(exchange);
    }

    // 返回的数字为0，加载这个过滤器的顺序，一般数字越小优先级越高
    // 这里是个全局过滤器给0就行了
    @Override
    public int getOrder() {
        return 0;
    }
}
