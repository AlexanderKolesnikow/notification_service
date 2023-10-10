package com.kite.kolesnikov.notificationservice.config.context;

import org.springframework.stereotype.Component;

@Component
public class UserContext {

    private final ThreadLocal<Long> userIdHolder = new ThreadLocal<>();

    public void setUserId(long userId) {
        userIdHolder.set(userId);
    }

    public long getUserId() {
        return userIdHolder.get() != null ? userIdHolder.get() : 0;
    }

    public void clear() {
        userIdHolder.remove();
    }
}
