package com.vacancy.vacancy_aggregator_core.model;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Notification {
    private String channelId;
    private String messageText;
    private String type;
}
