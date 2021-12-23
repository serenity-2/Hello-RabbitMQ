package com.example.hellorabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FruitConsumer {

    @RabbitListener(queues = "amqp.queue.fruit")
    public void consumerMsg(Message message) {
        String msg = new String(message.getBody());
        System.out.println("<---fruit queue--->" + msg);
    }
}
