package com.rabbitmq.springboot_rabbitmq.consumer;

import com.rabbitmq.springboot_rabbitmq.producer.EventProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EventConsumer {


    @Value("${rabbitmq.exchange}")
    private String queueName;

    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    private final static Logger logger = Logger.getLogger(EventProducer.class.getName());
    public EventConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){

        logger.info(String.format("Message Coming thru -> %s", message));

        rabbitTemplate.convertSendAndReceive(queueName,routingKey,message);
    }
}
