package com.example.hellorabbitmq.producer;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;


@Component
@RequestMapping("/send")
@ResponseBody
public class TopicMsgProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/msg01")
    public String sendMsg01() {
        String message01 = "the rabbit like eat orange";
        String routingKey01 = "quick.orange.rabbit";
        rabbitTemplate.convertAndSend("amqp_topic_exchange",routingKey01, message01,new CorrelationData(UUID.randomUUID().toString()));
        return "send msg01 ok";
    }

    @PostMapping("/msg02")
    public String sendMsg02() {
        String message01 = "the orange is delicious";
        String routingKey01 = "quick.orange.fox";
        rabbitTemplate.convertAndSend("amqp_topic_exchange",routingKey01,message01);
        return "send msg02 ok";
    }

    @PostMapping("/msg03")
    public String sendMsg03() {
        String message01 = "the delicious food is attractive";
        String routingKey01 = "lazy.pink.rabbit";
        rabbitTemplate.convertAndSend("amqp_topic_exchange",routingKey01,message01);
        return "send msg03 ok";
    }

    @PostMapping("/msg04")
    public String sendMsg04() {
        String message01 = "the attractive things is interesting";
        String routingKey01 = "quick.orange.male.rabbit";
        rabbitTemplate.convertAndSend("amqp_topic_exchange",routingKey01,message01);
        return "send msg04 ok";
    }

    @PostMapping("/msg05")
    public String sendMsg05() {
        String message01 = "the interesting person are worth hanging out with";
        String routingKey01 = "lazy.orange.male.rabbit";
        rabbitTemplate.convertAndSend("amqp_topic_exchange",routingKey01,message01);
        return "send msg05 ok";
    }
}
