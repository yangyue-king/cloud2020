package com.atguigu.springcloud.controller;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;



// 定义一个消息接受员（sink.class）
// sink表示是消费者，已和exchange绑定
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {
    @Value("${server.port}")
    private String serverPort;

    // 接收来自 MQ exchange的 Message，监听，有消息时自动响应
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者1号，接受："+message.getPayload()+"\t port:"+serverPort);
    }

}
