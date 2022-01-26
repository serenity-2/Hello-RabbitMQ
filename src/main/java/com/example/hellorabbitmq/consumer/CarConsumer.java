package com.example.hellorabbitmq.consumer;

import com.example.hellorabbitmq.message.Demo01Message;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@Component
public class CarConsumer {

//    @RabbitListener(queues = "amqp.queue.car")
//    public void onMessage(Message message, Channel channel) throws IOException {
//        log.info("[MSG FROM CarQueue]" + new String(message.getBody()));
//        //故意抛出异常
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//        int i  = 1 / 0;
//    }
}
