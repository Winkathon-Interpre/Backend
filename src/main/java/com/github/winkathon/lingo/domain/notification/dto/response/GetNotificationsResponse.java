package com.github.winkathon.lingo.domain.notification.dto.response;

import java.util.List;

import com.github.winkathon.lingo.domain.notification.schema.Notification;

import lombok.Builder;

@Builder
public record GetNotificationsResponse(

        List<Notification> notifications
) {
}
