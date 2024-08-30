package com.github.winkathon.interpre.domain.notification.service;

import static java.time.LocalDateTime.*;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.github.winkathon.interpre.common.api.dto.response.ApiResponse;
import com.github.winkathon.interpre.domain.notification.exception.NotificationNotFoundException;
import com.github.winkathon.interpre.domain.notification.repository.NotificationRepository;
import com.github.winkathon.interpre.domain.notification.schema.Notification;
import com.github.winkathon.interpre.domain.user.schema.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public ApiResponse<List<Notification>> getNotification(User user) {

        return ApiResponse.ok(notificationRepository.findByOwner(user));
    }

    public void readNotification(User user, String notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(NotificationNotFoundException::new);

        if (!notification.getOwner().equals(user)) {

            throw new NotificationNotFoundException();
        }

        notification.setRead(true);

        notificationRepository.save(notification);
    }

    public ApiResponse<Void> readAllNotification(User user) {

        notificationRepository.findByOwner(user).stream()
                .peek(notification -> notification.setRead(true))
                .forEach(notificationRepository::save);

        return ApiResponse.ok();
    }

    @Scheduled(cron = "0 0 0 * * *")
    private void deleteOldNotification() {

        notificationRepository.findAll().stream()
                .filter(Notification::isRead)
                .filter(notification -> notification.getCreatedAt().isBefore(now().minusDays(7)))
                .forEach(notificationRepository::delete);
    }
}
