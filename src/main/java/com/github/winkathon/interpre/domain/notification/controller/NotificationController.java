package com.github.winkathon.interpre.domain.notification.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.winkathon.interpre.common.api.dto.response.ApiResponse;
import com.github.winkathon.interpre.common.security.util.UserContext;
import com.github.winkathon.interpre.domain.notification.schema.Notification;
import com.github.winkathon.interpre.domain.notification.service.NotificationService;
import com.github.winkathon.interpre.domain.user.schema.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<List<Notification>> getNotification() {

        User user = UserContext.getUser();

        return notificationService.getNotification(user);
    }

    @PatchMapping("/read/{notificationId}")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Void> readNotification(@PathVariable String notificationId) {

        User user = UserContext.getUser();

        notificationService.readNotification(user, notificationId);

        return ApiResponse.ok();
    }

    @PatchMapping("/read/all")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Void> readAllNotification() {

        User user = UserContext.getUser();

        return notificationService.readAllNotification(user);
    }
}
