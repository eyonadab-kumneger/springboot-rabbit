package com.rabbitmq.springboot_rabbitmq.producer;

import com.rabbitmq.springboot_rabbitmq.dto.User;
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
public class JsonEventProducer {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value(("${rabbitmq.json.routing-key}"))
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(JsonEventProducer.class);

    public JsonEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){

        logger.info("Json message sent -> {}", user);
        rabbitTemplate.convertAndSend(exchange, routingKey, user);

    }
}
