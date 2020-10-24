package com.vacancy.notification_service.model;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    private String channelId;
    private String messageText;
    private String type;
}
