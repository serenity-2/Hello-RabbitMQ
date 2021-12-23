package com.example.hellorabbitmq.consumer;

import com.example.hellorabbitmq.message.Demo01Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// Demo01Consumer.java

@Component
@RabbitListener(queues = Demo01Message.QUEUE)
public class Demo01Consumer {

    @RabbitHandler
    public void onMessage(Demo01Message message) {
        System.out.println(message);
    }


}
