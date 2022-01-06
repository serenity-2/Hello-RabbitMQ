package com.example.hellorabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitProducerConfirmCallback implements RabbitTemplate.ConfirmCallback {

    public RabbitProducerConfirmCallback(RabbitTemplate rabbitTemplate) {
        rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            //RabbitMQ Broker成功接收到消息
            log.info("[confirm]--->confirm success<--- correlationData:{}", correlationData);
        } else {
            //RabbitMQ Broker未能接收到消息
            log.info("[confirm]--->confirm error<---- correlationData:{}", correlationData);
        }
    }
}
