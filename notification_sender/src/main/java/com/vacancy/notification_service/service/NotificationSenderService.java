package com.vacancy.notification_service.service;


import com.vacancy.notification_service.model.Notification;

/**
 * Interface for sand notifications
 *
 * @author Dmitriy G
 */
public interface NotificationSenderService {

    /**
     * Method for send notification
     *
     * @param notification notification for send
     *
     * @return true if success otherwise false
     */
    Boolean send(Notification notification);
}
