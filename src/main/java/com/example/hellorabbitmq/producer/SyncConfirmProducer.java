package com.example.hellorabbitmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class SyncConfirmProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 同步Confirm模式
     * @param message
     */
    public void syncSend(String message) {
        /**
         * <T> T invoke(OperationsCallback<T> action, @Nullable com.rabbitmq.client.ConfirmCallback acks, @Nullable com.rabbitmq.client.ConfirmCallback nacks)
         * action: 自定义操作
         * acks：定义接收到RabbitMQ Broker的成功响应后的回调
         * nacks: 定义接收到RabbitMQ Broker的失败响应后的回调
         */
        rabbitTemplate.invoke(rabbitOperations -> {
            String exchange = "amqp_topic_exchange";
            String routingKey = "quick.orange.rabbit";
            rabbitOperations.convertAndSend(exchange, routingKey, message);
            log.info("[doInRabbit] send message success");
            log.info("[doInRabbit] wait confirm ...");
            // timeout参数，0表示无限等待（从发送者发送消息到消费者接受消息再到生产者confirm这个过程需要的时间，如果等待时间小于这个时间则会超时异常）
            rabbitOperations.waitForConfirms(2000L);
            log.info("[doInRabbit] confirm finished");
            return null;
        }, (l, b) -> log.info("[handle] confim success"), (l, b) -> log.info("[handle] confim error"));
    }
}
