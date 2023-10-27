package com.kite.kolesnikov.notificationservice.service;

import com.kite.kolesnikov.notificationservice.dto.enums.PreferredContact;
import com.kite.kolesnikov.notificationservice.dto.UserDto;

public interface NotificationService {

    void send(UserDto user, String message);

    PreferredContact getPreferredContact();
}
