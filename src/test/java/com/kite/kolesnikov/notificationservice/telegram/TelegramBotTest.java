package com.kite.kolesnikov.notificationservice.telegram;

import com.kite.kolesnikov.notificationservice.config.telegram.TelegramBotConfig;
import com.kite.kolesnikov.notificationservice.entity.TelegramProfile;
import com.kite.kolesnikov.notificationservice.service.telegram.TelegramBot;
import com.kite.kolesnikov.notificationservice.service.telegram.TelegramProfileService;
import com.kite.kolesnikov.notificationservice.service.telegram.command.CommandExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@ExtendWith(MockitoExtension.class)
class TelegramBotTest {

    @InjectMocks
    private TelegramBot telegramBot;
    @Mock
    private TelegramProfileService telegramProfileService;
    @Mock
    private TelegramBotConfig config;
    @Mock
    private CommandExecutor commandExecutor;

    @Test
    void testOnUpdateReceived() {
        Update update = new Update();
        Message message = new Message();
        User user = new User();
        Chat chat = new Chat();
        chat.setId(123L);
        user.setUserName("testUser");
        message.setFrom(user);
        message.setText("testMessage");
        message.setChat(chat);
        update.setMessage(message);

        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(123L);
        responseMessage.setText("ResponseText");
        Mockito.when(commandExecutor.executeCommand(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(responseMessage);

        telegramBot.onUpdateReceived(update);

        Mockito.verify(commandExecutor).executeCommand(123L, "testUser", "testMessage");
    }

    @Test
    void testSendNotification() {
        TelegramProfile profile = new TelegramProfile();
        profile.setChatId(123L);
        Mockito.when(telegramProfileService.getByUserId(1L)).thenReturn(profile);

        telegramBot.sendNotification(1L, "testNotification");

        Mockito.verify(telegramProfileService).getByUserId(1L);
    }
}
