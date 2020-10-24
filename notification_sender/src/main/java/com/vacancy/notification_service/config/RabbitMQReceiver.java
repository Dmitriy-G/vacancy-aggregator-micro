package com.vacancy.notification_service.config;

import com.vacancy.notification_service.model.Notification;
import com.vacancy.notification_service.service.NotificationSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQReceiver {

    private NotificationSenderService notificationSenderService;

    @Autowired
    public RabbitMQReceiver(NotificationSenderService notificationSenderService) {
        this.notificationSenderService = notificationSenderService;
    }

    @RabbitListener(queues = "${rabbitmq.receiveQueueName}")
    public void receivedMessage(Notification notification) {
        log.info("Notification with channel id = " + notification.getChannelId() + " was received.");
        notificationSenderService.send(notification);
    }
}
