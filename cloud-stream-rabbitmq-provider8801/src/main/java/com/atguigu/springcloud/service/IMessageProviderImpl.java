package com.atguigu.springcloud.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

import java.util.UUID;

// 这里不要@Service，这里不是传统的和Dao打交道的
// 是和MQ 打交道的业务逻辑，用 @EnableBinding 指定 信道channel 和exchange绑定
// 这里是binder的消息源

@EnableBinding(Source.class)  // 定义一个消息源（source.class）
public class IMessageProviderImpl implements IMessageProvider{

     @Resource
    private MessageChannel output; //消息源（source.class）的发送管道

    @Override
    public String send()
    {
        String serial = UUID.randomUUID().toString();

        // MessageBuilder.withPayload(serial).build()返回的是 Message
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial: "+serial);
        return null;
    }

}


// source（本类） -> channel（output） -> binder -> exchange(topic)