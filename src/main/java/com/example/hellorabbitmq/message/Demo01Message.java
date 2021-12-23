package com.example.hellorabbitmq.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class Demo01Message implements Serializable {
    public static final String QUEUE = "jzjr_queue_dev";

    public static final String EXCHANGE = "jzjr_exchange_dev";

    public static final String ROUTING_KEY = "jzjr_dead_routing_dev";

    private String message;

}
