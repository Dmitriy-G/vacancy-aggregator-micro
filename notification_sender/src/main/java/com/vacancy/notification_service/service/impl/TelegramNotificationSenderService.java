package com.vacancy.notification_service.service.impl;


import com.vacancy.notification_service.model.Notification;
import com.vacancy.notification_service.sender.TelegramBot;
import com.vacancy.notification_service.service.NotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelegramNotificationSenderService implements NotificationSenderService {

    private TelegramBot telegramBot;

    @Autowired
    public TelegramNotificationSenderService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public Boolean send(Notification notification) {
        String chatId = notification.getChannelId();
        String messageText = notification.getMessageText();
        telegramBot.sendMsg(chatId, messageText);
        return true;
    }
}
