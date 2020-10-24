package com.vacancy.notification_service;

import com.vacancy.notification_service.model.Notification;
import com.vacancy.notification_service.service.impl.TelegramResponseService;
import org.junit.jupiter.api.Test;


class TelegramResponseServiceTest {

    private TelegramResponseService telegramResponseService = new TelegramResponseService();

    @Test
    void send() {
        telegramResponseService.response(Notification.builder().build());
    }
}
