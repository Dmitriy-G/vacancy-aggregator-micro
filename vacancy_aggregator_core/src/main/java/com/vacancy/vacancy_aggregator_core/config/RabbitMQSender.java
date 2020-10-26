package com.vacancy.vacancy_aggregator_core.config;

import com.vacancy.vacancy_aggregator_core.model.Notification;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rabbitmq")
@Slf4j
@Setter
public class RabbitMQSender {
    private final AmqpTemplate rabbitTemplate;
    private String exchange;
    private String routingKey;

    @Autowired
    public RabbitMQSender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Notification notification) {
        rabbitTemplate.convertAndSend(exchange, routingKey, notification);
    }
}
