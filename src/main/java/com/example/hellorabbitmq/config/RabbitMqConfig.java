package com.example.hellorabbitmq.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@Configuration
public class RabbitMqConfig {
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(factory);
        //解决: Caused by: org.springframework.amqp.AmqpException: No method found for class [B 异常
        //消息转换器,消息转化为json, setMessageConverter(MessageConverter ),创建一个Jackson2JsonMessageConverter对象放进去
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory factory){
        SimpleRabbitListenerContainerFactory srlcf = new SimpleRabbitListenerContainerFactory();
        srlcf.setConnectionFactory(factory);
        //当消息有异常内容时（例如类型不匹配），将不再重新放入队列，直接丢弃
        srlcf.setDefaultRequeueRejected(false);

        //设置消息转为json
        Jackson2JsonMessageConverter j2jmc = new Jackson2JsonMessageConverter();
        srlcf.setMessageConverter(j2jmc);
        return srlcf;
    }

    //发送消息时如不配置序列化方法则按照java默认序列化机制，则会造成发送编码不符合
    @Bean
    public MessageConverter messageConverter(){
        Jackson2JsonMessageConverter j2jmc = new Jackson2JsonMessageConverter();
        return j2jmc;
    }
}
