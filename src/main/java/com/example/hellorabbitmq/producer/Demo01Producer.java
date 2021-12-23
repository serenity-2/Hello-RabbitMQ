package com.example.hellorabbitmq.producer;


import com.example.hellorabbitmq.message.Demo01Message;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Component
@RequestMapping("/mq")
public class Demo01Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private AmqpAdmin amqpAdmin;

    @PostMapping("/send/direct")
    public void sendSimpleMessage() {
        Demo01Message demo01Message = new Demo01Message();
        demo01Message.setMessage("bioscience is importmant branch for future");
        rabbitTemplate.convertAndSend(Demo01Message.EXCHANGE,Demo01Message.ROUTING_KEY,demo01Message);
    }

    @PostMapping("/create/banding")
    public void sendDefaultMessage() {
        System.out.println("create exchange ...");
        amqpAdmin.declareExchange(new TopicExchange("amqp_topic_exchange"));
        System.out.println("exchange create success");
        System.out.println("create queue ...");
        String s = amqpAdmin.declareQueue(new Queue("amqp.queue.animals"));
        System.out.println("amqp.queue.animals create success");
        String s1 = amqpAdmin.declareQueue(new Queue("amqp.queue.fruit"));
        System.out.println("amqp.queue.fruit create success");
        System.out.println("exchange banding queue...");
        amqpAdmin.declareBinding(new Binding("amqp.queue.animals",Binding.DestinationType.QUEUE,"amqp_topic_exchange","*.orange.*",null));
        System.out.println("amqp.queue.animals banding amqp_topic_exchange success (*.orange.*)");
        amqpAdmin.declareBinding(new Binding("amqp.queue.fruit",Binding.DestinationType.QUEUE,"amqp_topic_exchange","*.*.rabbit",null));
        System.out.println("amqp.queue.fruit banding amqp_topic_exchange success (*.*.rabbit)");
        amqpAdmin.declareBinding(new Binding("amqp.queue.fruit",Binding.DestinationType.QUEUE,"amqp_topic_exchange","lazy.#",null));
        System.out.println("amqp.queue.fruit banding amqp_topic_exchange success (lazy.#)");
    }
}
