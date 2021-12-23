package com.example.hellorabbitmq.consumer;

import com.alibaba.fastjson.JSON;
import com.example.hellorabbitmq.bo.VegetableBO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VetegableConsumer {
    /**
     * 消息类型为单个实体对象
     * @param message
     */
    @RabbitListener(queues = "amqp.queue.vegetable")
    public void consumerMsg(Message message) {
        VegetableBO vegetableBO = JSON.parseObject(message.getBody(), VegetableBO.class);
        System.out.println(vegetableBO);
        System.out.println("<---fruit queue--->" + message);
    }

    /**
     * 1.消息类型为集合对象
     * 2.在已经创建队列、交换机和绑定关系的情况下，没必要在@RabbitListener
     * 注解里面重复指定了，如果非要写作用相当于注释，方便理解它们之间的关系，
     * 如果之前没有创建这些，则会创建
     * @param message
     */
//    @RabbitListener(bindings = @QueueBinding(
//            exchange = @Exchange(value = "amqp_topic_exchange",durable = "true",type = "topic"),
//            value = @Queue(value = "amqp.queue.vegetable",durable = "true"),
//            key = "vegetable.*"
//    ))
    @RabbitListener(queues = "amqp.queue.vegetable")
    public void consumerMsgs(Message message) {
        String s =  new String(message.getBody());
        List<VegetableBO> vegetableBOList = JSON.parseArray(s, VegetableBO.class);
        System.out.println(vegetableBOList);
        System.out.println("<---vegetable queue--->" + message);
    }
}
