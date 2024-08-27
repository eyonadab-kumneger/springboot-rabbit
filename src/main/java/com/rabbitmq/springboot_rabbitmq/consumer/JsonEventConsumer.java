package com.rabbitmq.springboot_rabbitmq.consumer;

import com.rabbitmq.springboot_rabbitmq.dto.User;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class JsonEventConsumer {

    private static final Logger logger = Logger.getLogger(JsonEventConsumer.class.getName());

   /* @RabbitListener(queues = {"${rabbitmq.json.queue}"})
    public void receiveMessage(User user){

        logger.info(String.format("Received json message -> %s",user.toString()));

    }*/
}
