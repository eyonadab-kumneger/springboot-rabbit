package com.rabbitmq.springboot_rabbitmq.producer;

import com.rabbitmq.springboot_rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JsonEventProducer {

    @Value("${rabbitmq.exchange}")
    private String exchange;


    @Value(("${rabbitmq.json.routing-key}"))
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonEventProducer.class);

    public JsonEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){

        LOGGER.info(String.format("Json message sent -> %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange,routingKey,user);

    }
}
