package com.example.hellorabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class RabbitMqConfig {

    public static class directExchangeDemoConfiguration {

        @Bean
        public Queue carQueue() {
            //声明一个car队列
            return QueueBuilder.durable("amqp.queue.car")
                    //指定dead queue的Exchange
                    .deadLetterExchange("amqp_direct_car")
                    //指定dead queue的路由key，如果不指定默认使用发送消息的路由key
                    .deadLetterRoutingKey("car.dead")
                    .build();
        }

        /**
         * 声明一个car交换机
         * @return
         */
        @Bean
        public DirectExchange carExchange() {
            return new DirectExchange("amqp_direct_car",true,false);
        }
        /**
         * 声明一个dead queue
         * @return
         */
        @Bean
        public Queue deadQueue() {
            return new Queue("amqp.queue.dead",true,false,false);
        }
        /**
         * car queue 绑定 car exchange
         * @return
         */
        @Bean
        public Binding carBinding() {
            return BindingBuilder.bind(carQueue()).to(carExchange()).with("car.star");
        }
        /**
         * dead queue 绑定 car exchange
         * @return
         */
        @Bean
        public Binding deadBinding() {
            return BindingBuilder.bind(deadQueue()).to(carExchange()).with("car.dead");
        }

        @Bean
        public Queue delayQueue() {
            return QueueBuilder.durable("delay.queue.theme").build();
        }

        @Bean
        public CustomExchange delayExchange() {
            Map<String, Object> args = new HashMap<>();
            args.put("x-delayed-type","direct");
            return new CustomExchange("amqp_delay_theme","x-delayed-message",true,false,args);

        }
        @Bean
        public Binding blinding() {
            return BindingBuilder.bind(deadQueue()).to(delayExchange()).with("delay").noargs();
        }
    }
    @Autowired
    RabbitTemplate rabbitTemplate;

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

    /**
     发送消息时如不配置序列化方法则按照java默认序列化机制，则会造成发送编码不符合
     */
    @Bean
    public MessageConverter messageConverter(){
        Jackson2JsonMessageConverter j2jmc = new Jackson2JsonMessageConverter();
        return j2jmc;
    }
}
