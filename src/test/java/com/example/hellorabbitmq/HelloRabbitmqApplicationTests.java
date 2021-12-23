package com.example.hellorabbitmq;

import com.example.hellorabbitmq.message.Demo01Message;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "dev")
class HelloRabbitmqApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    void contextLoads() {

    }

    @Test
    public void receiveMsg() {
        //只有主动调用该方法才会去队列里面拿消息
        String message = (String) rabbitTemplate.receiveAndConvert("amqp.queue.animals");
        System.out.println("<---animal queue--->" + message);
    }
}
