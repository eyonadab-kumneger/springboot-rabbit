package com.rabbitmq.springboot_rabbitmq.consumer;

import com.rabbitmq.springboot_rabbitmq.producer.EventProducer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EventConsumer {


    private final static Logger logger = Logger.getLogger(EventProducer.class.getName());

    @RabbitListener(queues = {"${rabbitmq.queue}"})
    public void sendMessage(String message){

        logger.info(String.format("Message received -> %s", message));

    }
}
