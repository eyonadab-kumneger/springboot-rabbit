package com.rabbitmq.springboot_rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class StringEventConsumer {

    private final static Logger logger = LoggerFactory.getLogger(StringEventConsumer.class);

    @RabbitListener(queues = "${rabbitmq.string-queue}")
    public void sendMessage(String message){
        logger.info("String message received -> {}", message);

    }
}
