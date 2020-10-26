package com.vacancy.vacancy_aggregator_core.service;


import com.vacancy.vacancy_aggregator_core.model.Notification;
import com.vacancy.vacancy_aggregator_core.model.Vacancy;

import java.util.List;

public interface NotificationHelperService {
    List<Notification> generateNotifications(Vacancy vacancy);
    void sendVacancyToSubscribers(Vacancy vacancy);
    void addReceivedVacancyToSubscriberList(String chatId, Vacancy vacancy);
}
