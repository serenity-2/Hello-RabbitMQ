package com.example.hellorabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class FruitConsumer {

    @RabbitListener(queues = "amqp.queue.fruit")
    public void consumerMsg(Message message) throws InterruptedException {
        System.out.println("<---fruit queue--->" + message);
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
