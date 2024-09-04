package com.github.winkathon.tripee.domain.notification.dto.response;

import java.util.List;

import com.github.winkathon.tripee.domain.notification.schema.Notification;

import lombok.Builder;

@Builder
public record GetNotificationsResponse(

        List<Notification> notifications
) {
}
