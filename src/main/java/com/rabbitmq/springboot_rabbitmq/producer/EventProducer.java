package com.rabbitmq.springboot_rabbitmq.producer;

import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EventProducer {

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    private final static Logger logger = Logger.getLogger(EventProducer.class.getName());
    public EventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){

        logger.info(String.format("Message Coming thru -> %s", message));

        rabbitTemplate.convertSendAndReceive(exchangeName,routingKey,message);
    }
}
