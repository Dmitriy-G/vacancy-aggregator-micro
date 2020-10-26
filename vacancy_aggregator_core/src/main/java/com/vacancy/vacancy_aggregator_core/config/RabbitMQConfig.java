package com.vacancy.vacancy_aggregator_core.config;

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
    private String queueName;
    private String exchange;
    private String routingKey;

    @Bean
    Queue receiveQueue() {
        return new Queue(queueName);
    }

    @Bean
    DirectExchange directExchange() {
        return ExchangeBuilder.directExchange(exchange)
                .build();
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(receiveQueue()).to(directExchange()).with(routingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
