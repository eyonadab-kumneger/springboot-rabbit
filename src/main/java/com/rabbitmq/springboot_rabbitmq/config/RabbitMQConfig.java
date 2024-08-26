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

    @Value("${rabbitmq.queue}")
    private String queueName;

    @Value("${rabbitmq.json.queue}")
    private String jsonQueueName;
    @Value("${rabbitmq.exchange}")
    private String exchangeName;
    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    @Value("${rabbitmq.json.routing-key}")
    private String jsoRoutingKey;

    @Bean
    public Queue queue(){

        return new Queue(queueName);
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
    public Binding binding(){

        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public Binding jsonBinding(){

        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(jsoRoutingKey);
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
