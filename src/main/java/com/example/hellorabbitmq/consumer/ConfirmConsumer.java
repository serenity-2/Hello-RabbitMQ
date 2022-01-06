package com.example.hellorabbitmq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.example.hellorabbitmq.bo.VegetableBO;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ConfirmConsumer {

    @RabbitListener(queues = "amqp.queue.fruit")
    public void receiveMsg(VegetableBO vegetableBO, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        if (vegetableBO.getName().equals("apple")) {
            log.info("i like apple.  deliveryTag:{}",deliveryTag);
            channel.basicAck(deliveryTag,false);
        }else {
            log.info("i don't want anything else");
            channel.basicNack(deliveryTag,false,false);
        }
    }

    @RabbitListener(queues = "amqp.queue.vegetable")
    public void receiveMsg2(Message message, Channel channel) throws IOException {
        VegetableBO vegetableBO = JSONObject.parseObject(message.getBody(), VegetableBO.class);
        if (vegetableBO.getName().equals("corn")) {
            log.info("i like corn.  deliveryTag:{}",message.getMessageProperties().getDeliveryTag());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }else {
            log.info("i don't want anything else");
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
        }
    }
}
