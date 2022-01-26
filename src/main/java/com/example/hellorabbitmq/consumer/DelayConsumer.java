package com.example.hellorabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class DelayConsumer {

    @RabbitListener(queues = "amqp.queue.car")
    public void receiveDelayMsg(Message message, Channel channel) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        log.info("消息接受时间:{}",dateFormat.format(new Date()));
        log.info("接受到消息:{}",new String(message.getBody()));
    }

}
