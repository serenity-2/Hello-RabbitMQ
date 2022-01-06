package com.example.hellorabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class AnimalConsumer {

    @RabbitListener(queues = "amqp.queue.animals")
    public void initiativeConsumerMsg(Message message, Channel channel) throws InterruptedException {
        System.out.println("<---animal queue--->" + message);
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}


