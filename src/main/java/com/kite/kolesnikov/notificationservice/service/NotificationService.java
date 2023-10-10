package com.kite.kolesnikov.notificationservice.service;


import com.kite.kolesnikov.notificationservice.dto.UserDto;

public interface NotificationService {

    void send(UserDto user, String message);

    UserDto.PreferredContact getPreferredContact();
}
