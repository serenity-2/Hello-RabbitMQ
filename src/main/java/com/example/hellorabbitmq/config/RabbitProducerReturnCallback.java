package com.example.hellorabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitProducerReturnCallback implements RabbitTemplate.ReturnsCallback {
    public RabbitProducerReturnCallback(RabbitTemplate rabbitTemplate) {
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
      log.info("[returnedMessage] message:{}-----repolyCode:{}-----repolyText:{}-----exchange:{}-----routingkey:{}",
              returnedMessage.getMessage(),returnedMessage.getReplyCode(),returnedMessage.getReplyText(),returnedMessage.getExchange(),returnedMessage.getRoutingKey());
    }
}
