package com.kite.kolesnikov.notificationservice.service.telegram;

import com.kite.kolesnikov.notificationservice.dto.enums.PreferredContact;
import com.kite.kolesnikov.notificationservice.dto.UserDto;
import com.kite.kolesnikov.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramService implements NotificationService {
    private final TelegramBot telegramBot;

    @Override
    public void send(UserDto user, String message) {
        telegramBot.sendNotification(user.getId(), message);
    }

    @Override
    public PreferredContact getPreferredContact() {
        return PreferredContact.TELEGRAM;
    }
}
