package com.example.hellorabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableRabbit
public class HelloRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloRabbitmqApplication.class, args);
    }

}
