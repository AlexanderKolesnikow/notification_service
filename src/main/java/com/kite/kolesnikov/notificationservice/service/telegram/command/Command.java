package com.kite.kolesnikov.notificationservice.service.telegram.command;

import com.kite.kolesnikov.notificationservice.client.UserServiceClient;
import com.kite.kolesnikov.notificationservice.service.telegram.TelegramProfileService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Locale;

@AllArgsConstructor
public abstract class Command {
    protected static final Locale LOCALE_DEFAULT = Locale.getDefault();
    protected final MessageSource messageSource;
    protected final TelegramProfileService telegramProfileService;
    protected final UserServiceClient userServiceClient;

    public abstract SendMessage execute(long chatId, String userName);
}
