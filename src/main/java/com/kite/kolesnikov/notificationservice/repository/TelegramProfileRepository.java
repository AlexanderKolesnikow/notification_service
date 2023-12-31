package com.kite.kolesnikov.notificationservice.repository;


import com.kite.kolesnikov.notificationservice.entity.TelegramProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelegramProfileRepository extends JpaRepository<TelegramProfile, Long> {

    boolean existsByUserName(String userName);

    Optional<TelegramProfile> getByUserId(Long userId);
}
