package com.kite.kolesnikov.notificationservice.config.telegram;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class TelegramBotConfig {

    @Value("${telegram-bot.name}")
    private String name;
    @Value("${telegram-bot.token}")
    private String token;
}
