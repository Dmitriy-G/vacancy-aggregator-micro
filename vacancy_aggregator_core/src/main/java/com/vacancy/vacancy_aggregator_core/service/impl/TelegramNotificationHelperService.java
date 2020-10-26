package com.vacancy.vacancy_aggregator_core.service.impl;

import com.vacancy.vacancy_aggregator_core.config.RabbitMQSender;
import com.vacancy.vacancy_aggregator_core.model.Notification;
import com.vacancy.vacancy_aggregator_core.model.Vacancy;
import com.vacancy.vacancy_aggregator_core.service.NotificationHelperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TelegramNotificationHelperService implements NotificationHelperService {

    private final String RABOTA_UA_URL = "https://rabota.ua/company1/vacancy";
    private final String TELEGRAM_NOTIFICATION_TYPE = "Telegram";

    private RabbitMQSender rabbitMQSender;

    @Autowired
    public TelegramNotificationHelperService(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @Override
    public List<Notification> generateNotifications(Vacancy vacancy) {
        //TODO: get subscribers from storage service
        //List<String> subscribersIds = storageService.getSubscribersIdList();
        List<Notification> notifications = new ArrayList<>();
        String notificationText = generateNotificationText(vacancy);
        /*for (String subscriberId: subscribersIds) {
            Notification notification = new Notification(subscriberId, notificationText, TELEGRAM_NOTIFICATION_TYPE);
            notifications.add(notification);
        }*/
        notifications.add(Notification.builder()
                .channelId("")
                .messageText(notificationText)
                .type(TELEGRAM_NOTIFICATION_TYPE)
                .build());
        return notifications;
    }

    @Override
    public void sendVacancyToSubscribers(Vacancy vacancy) {
        List<Notification> notifications = generateNotifications(vacancy);
        for (Notification notification: notifications) {
            String subscriberId = notification.getChannelId();
            //TODO: add to RabbitMQ here
            rabbitMQSender.send(notification);
            //TODO: get findVacanciesIdsBySubscriberId from storage service
            /*Set<String> sentVacanciesId = storageService.findVacanciesIdsBySubscriberId(subscriberId);
            if (!sentVacanciesId.contains(vacancy.getId())) {
                Boolean result = senderService.send(notification);
                if (result) {
                    addReceivedVacancyToSubscriberList(subscriberId, vacancy);
                }
            } else {
                log.info("Vacancy with id " + vacancy.getId() + " already sent to subscriber " + subscriberId);
            }*/
        }
    }

    @Override
    public void addReceivedVacancyToSubscriberList(String chatId, Vacancy vacancy) {
        //TODO: use storage service here
        //storageService.addReceivedVacancyToSubscriberList(chatId, vacancy);
    }

    private String generateNotificationText(Vacancy vacancy) {
        StringBuilder builder = new StringBuilder();
        builder.append("[.](").append(vacancy.getDesignBannerUrl()).append(")")
                .append("\n")
                .append("*Компания: ")
                .append(vacancy.getCompanyName()).append("*\n")
                .append("*Позиция:* ")
                .append(vacancy.getName()).append("\n")
                .append("*Город:* ")
                .append(vacancy.getCityName()).append("\n")
                .append("*Краткое описание:* ")
                .append(vacancy.getShortDescription()).append("\n")
                .append("*Дата размещения:* ").append(vacancy.getDate()).append("\n\n")
                .append("*Ссылка на вакансию:* ").append(RABOTA_UA_URL).append(vacancy.getId()).append("\n\n");
        return builder.toString();
    }
}
