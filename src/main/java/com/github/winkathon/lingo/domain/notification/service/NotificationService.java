package com.github.winkathon.lingo.domain.notification.service;

import static java.time.LocalDateTime.*;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.github.winkathon.lingo.domain.notification.dto.response.GetNotificationsResponse;
import com.github.winkathon.lingo.domain.notification.exception.NotificationNotFoundException;
import com.github.winkathon.lingo.domain.notification.repository.NotificationRepository;
import com.github.winkathon.lingo.domain.notification.schema.Notification;
import com.github.winkathon.lingo.domain.user.schema.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public GetNotificationsResponse getNotifications(User user) {

        List<Notification> notifications = notificationRepository.findAll()
                .stream().filter(notification -> notification.getOwner().equals(user)).toList();

        return GetNotificationsResponse.builder()
                .notifications(notifications)
                .build();
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

    public void readAllNotification(User user) {

        notificationRepository.findByOwner(user).stream()
                .peek(notification -> notification.setRead(true))
                .forEach(notificationRepository::save);
    }

    @Scheduled(cron = "0 0 0 * * *")
    private void deleteOldNotification() {

        notificationRepository.findAll().stream()
                .filter(Notification::isRead)
                .filter(notification -> notification.getCreatedAt().isBefore(now().minusDays(7)))
                .forEach(notificationRepository::delete);
    }
}
