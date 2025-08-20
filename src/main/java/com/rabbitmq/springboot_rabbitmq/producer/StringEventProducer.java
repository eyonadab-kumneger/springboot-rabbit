package com.rabbitmq.springboot_rabbitmq.producer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class StringEventProducer {

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.string-routing-key}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    private final static Logger logger = LoggerFactory.getLogger(StringEventProducer.class);

    public void sendMessage(String message){

        logger.info("Message Coming thru -> {}", message);
        
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
