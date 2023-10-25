package com.kite.kolesnikov.notificationservice.telegram.command;

import com.kite.kolesnikov.notificationservice.client.UserServiceClient;
import com.kite.kolesnikov.notificationservice.service.telegram.TelegramProfileService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

@ExtendWith(MockitoExtension.class)
public class CommandTest {
    @Mock
    protected MessageSource messageSource;
    @Mock
    protected TelegramProfileService telegramProfileService;
    @Mock
    protected UserServiceClient userServiceClient;
}
