package com.example.hellorabbitmq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class DeadConsumer {

    @RabbitListener(queues = "delay.queue.theme")
    public void receiveDeadMsg(Message message, Channel channel) throws IOException {
        log.info("[MSG FROM DEADQUEUE]" + new String(message.getBody()));
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
