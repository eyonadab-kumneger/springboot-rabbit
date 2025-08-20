package com.rabbitmq.springboot_rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.string-queue}")
    private String stringQueueName;

    @Value("${rabbitmq.json.queue}")
    private String jsonQueueName;

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.string-routing-key}")
    private String routingKey;

    @Value("${rabbitmq.json.routing-key}")
    private String jsonRoutingKey;

    @Bean
    public Queue stringQueueName(){
        return new Queue(stringQueueName);
    }

    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueueName);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding stringBinding(){
        return BindingBuilder.bind(stringQueueName())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(jsonRoutingKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbit = new RabbitTemplate(connectionFactory);
        rabbit.setMessageConverter(converter());

        return rabbit;
    }

}
