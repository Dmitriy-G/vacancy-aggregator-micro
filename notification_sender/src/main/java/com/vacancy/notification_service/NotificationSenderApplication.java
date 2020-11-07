package com.vacancy.notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties
public class NotificationSenderApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(NotificationSenderApplication.class, args);
    }

}
