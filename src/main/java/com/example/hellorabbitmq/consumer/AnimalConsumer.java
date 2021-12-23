package com.example.hellorabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AnimalConsumer {

    @RabbitListener(queues = "amqp.queue.animals")
    public void initiativeConsumerMsg(Message message, Channel channel) {
        System.out.println(new String(message.getBody()));
        System.out.println("<---animal queue--->" + message);
    }
}


