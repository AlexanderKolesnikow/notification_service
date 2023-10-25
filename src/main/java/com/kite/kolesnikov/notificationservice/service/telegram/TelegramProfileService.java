package com.kite.kolesnikov.notificationservice.service.telegram;

import com.kite.kolesnikov.notificationservice.entity.TelegramProfile;
import com.kite.kolesnikov.notificationservice.exception.EntityNotFoundException;
import com.kite.kolesnikov.notificationservice.repository.TelegramProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramProfileService {
    private final TelegramProfileRepository telegramProfileRepository;

    @Transactional
    public void save(TelegramProfile telegramProfile) {
        telegramProfileRepository.save(telegramProfile);
    }

    @Transactional(readOnly = true)
    public boolean existsByUserName(String userName) {
        return telegramProfileRepository.existsByUserName(userName);
    }

    @Transactional(readOnly = true)
    public TelegramProfile getByUserId(long userId) {
        return telegramProfileRepository.getByUserId(userId)
                .orElseThrow(() -> {
                    String errorMessage = MessageFormat.format("Telegram profile of user {} not found ", userId);
                    log.error(errorMessage);
                    return new EntityNotFoundException(errorMessage);
                });
    }
}
