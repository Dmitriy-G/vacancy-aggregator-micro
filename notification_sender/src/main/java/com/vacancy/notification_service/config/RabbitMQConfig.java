package com.vacancy.notification_service.config;

import lombok.Setter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
@Setter
public class RabbitMQConfig {
    private String receiveQueueName;
    /*private String receiveExchange;
    private String receiveRoutingKey;*/

    @Bean
    Queue receiveQueue() {
        return new Queue(receiveQueueName);
    }

   /* @Bean
    DirectExchange directExchange() {
        return ExchangeBuilder.directExchange(receiveExchange)
                .build();
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(receiveQueue()).to(directExchange()).with(receiveRoutingKey);
    }*/

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
