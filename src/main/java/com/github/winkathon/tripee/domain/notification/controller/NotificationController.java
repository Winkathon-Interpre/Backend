package com.github.winkathon.tripee.domain.notification.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.winkathon.tripee.common.api.dto.response.ApiResponse;
import com.github.winkathon.tripee.common.security.util.UserContext;
import com.github.winkathon.tripee.domain.notification.dto.response.GetNotificationsResponse;
import com.github.winkathon.tripee.domain.notification.service.NotificationService;
import com.github.winkathon.tripee.domain.user.schema.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<GetNotificationsResponse> getNotifications() {

        User user = UserContext.getUser();

        return ApiResponse.ok(notificationService.getNotifications(user));
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

        notificationService.readAllNotification(user);

        return ApiResponse.ok();
    }
}
