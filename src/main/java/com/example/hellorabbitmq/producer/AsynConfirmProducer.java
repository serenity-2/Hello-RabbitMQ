package com.example.hellorabbitmq.producer;

import com.example.hellorabbitmq.bo.VegetableBO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class AsynConfirmProducer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 异步确认模式
     */
    public void asynSend(String message) {
        String exchange = "amqp_topic_exchange";
        String routingKey = "quick.orange.rabbit";
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }

    /**
     * 异步确认模式-无法将消息发送到queue
     */
    public void asynSendLostToQueue(String message) {
        String exchange = "amqp_topic_exchange";
        String routingKey = "error";
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }

    /**
     * 异步确认模式-测试消费者消息确认机制
     */
    public void sendMsg01() {
        String exchange = "amqp_topic_exchange";
        String routingKey = "vegetable.corn";
        VegetableBO corn = new VegetableBO().setName("corn").setColor("yellow").setValue(3.5);
        rabbitTemplate.convertAndSend(exchange,routingKey,corn);
    }
}
