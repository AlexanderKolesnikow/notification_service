package com.kite.kolesnikov.notificationservice.service.telegram.command;

import com.kite.kolesnikov.notificationservice.client.UserServiceClient;
import com.kite.kolesnikov.notificationservice.entity.TelegramProfile;
import com.kite.kolesnikov.notificationservice.service.telegram.TelegramProfileService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Slf4j
@Component(value = "/start")
public class StartCommand extends Command {

    @Autowired
    public StartCommand(MessageSource messageSource,
                        TelegramProfileService telegramProfileService,
                        UserServiceClient userServiceClient) {
        super(messageSource, telegramProfileService, userServiceClient);
    }

    @Override
    public SendMessage execute(long chatId, String userName) {
        log.info("Executing command for chatId: {} with userName: {}", chatId, userName);

        String response;
        if (!telegramProfileService.existsByUserName(userName)) {
            log.info("User {} not found. Processing user.", userName);
            response = processUser(chatId, userName);
        } else {
            response = messageSource.getMessage("telegram.start.on_the_system", null, LOCALE_DEFAULT);
        }

        return buildMessage(chatId, response);
    }

    private String processUser(long chatId, String userName) {
        String response;
        try {
            long userId = userServiceClient.getUserIdByContact(userName);
            TelegramProfile telegramProfile = buildTelegramProfile(chatId, userName, userId);
            telegramProfileService.save(telegramProfile);

            log.info("Telegram profile saved for user: {}", userName);

            response = messageSource.getMessage("telegram.start", new Object[]{userName}, LOCALE_DEFAULT);
        } catch (FeignException e) {
            log.error("Error occurred while processing user {}. Exception: {}", userName, e.getMessage());
            response = messageSource.getMessage(
                    "telegram.start.not_registered", new Object[]{userName}, LOCALE_DEFAULT);
        }

        return response;
    }

    private SendMessage buildMessage(long chatId, String response) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(response);

        log.info("Message built for chatId: {}. Response: {}", chatId, response);

        return message;
    }

    private TelegramProfile buildTelegramProfile(long chatId, String userName, long userId) {
        return TelegramProfile.builder()
                .chatId(chatId)
                .userName(userName)
                .userId(userId)
                .build();
    }
}
