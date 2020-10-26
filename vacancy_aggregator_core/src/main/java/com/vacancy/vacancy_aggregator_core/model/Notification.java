package com.vacancy.vacancy_aggregator_core.model;

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
