package com.vacancy.notification_service.sender;

import com.vacancy.notification_service.model.Notification;
import com.vacancy.notification_service.service.ResponseService;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

/**
 * Bot for send notifications via telegram
 *
 * @author Dmitriy G
 */
@Configuration
@ConfigurationProperties(prefix = "telegram")
@Slf4j
@Setter
public class TelegramBot extends TelegramLongPollingBot {

    private String telegramBotName;
    private String telegramBotToken;
    private String type;

    private ResponseService responseService;

    @Autowired
    public TelegramBot(ResponseService responseService) {
        this.responseService = responseService;
    }

    @PostConstruct
    private void postConstruct() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method for receive message from user
     *
     * @param update contains message from user
     */
    @Override
    public void onUpdateReceived(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String messageText = update.getMessage().getText();
        Notification notification = Notification.builder()
                .channelId(chatId)
                .messageText(messageText)
                .type(type)
                .build();
        responseService.response(notification);
        switch (messageText) {
            case "subscribe": {
                String message = "You was added to subscribers";
                sendMsg(chatId, message);
                break;
            }
            case "stop": {
                String message = "You was removed from subscribers";
                sendMsg(chatId, message);
                break;
            }
            default: {
                String message = "Unknown command: " + update.getMessage().getText();
                sendMsg(chatId, message);
            }
        }
    }

    /**
     * Method for get bot username
     *
     * @return the username
     */
    @Override
    public String getBotUsername() {
        return telegramBotName;
    }

    /**
     * Method for get bot token
     *
     * @return the token
     */
    @Override
    public String getBotToken() {
        return telegramBotToken;
    }

    /**
     * Method for sending message to chat
     * @param chatId id of the chat
     * @param message the message for user
     */
    @SneakyThrows
    public void sendMsg(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        log.debug("Chat id = " + chatId);
        log.debug("Message = " + message);
        execute(sendMessage);
    }
}
