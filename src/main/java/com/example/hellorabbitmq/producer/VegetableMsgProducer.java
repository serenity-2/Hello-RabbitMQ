package com.example.hellorabbitmq.producer;

import com.example.hellorabbitmq.bo.VegetableBO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Component
@RequestMapping("/vegetable")
public class VegetableMsgProducer {

@Resource
private RabbitTemplate rabbitTemplate;

    @PostMapping("/msg01")
    public String msg01() {
        String exchange = "amqp_topic_exchange";
        String routingKey = "vegetable.carrot";
        VegetableBO greenVegetable = new VegetableBO().setName("青菜").setColor("绿色/白色").setValue(2.5);
        rabbitTemplate.convertAndSend(exchange,routingKey,greenVegetable);
        return "send msg01 success";
    }

    @PostMapping("/msg02")
    public String msg02() {
        String exchange = "amqp_topic_exchange";
        String routingKey = "vegetable.carrot";
        VegetableBO chineseCabbage = new VegetableBO().setName("白菜").setColor("绿色/白色").setValue(2.2);
        VegetableBO spinach = new VegetableBO().setName("菠菜").setColor("绿色").setValue(3.5);
        VegetableBO carrot = new VegetableBO().setName("胡萝卜").setColor("绿色/白色").setValue(1.2);
        List<VegetableBO> rabbitBOList = Arrays.asList(chineseCabbage, spinach, carrot);
        rabbitTemplate.convertAndSend(exchange,routingKey,rabbitBOList);
        return "send msg02 success";
    }
}
