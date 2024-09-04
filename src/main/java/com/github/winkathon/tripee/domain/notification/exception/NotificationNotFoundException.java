package com.github.winkathon.tripee.domain.notification.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.tripee.common.api.exception.ApiException;

public class NotificationNotFoundException extends ApiException {

    public NotificationNotFoundException() {

        super(HttpStatus.NOT_FOUND, "알림을 찾을 수 없습니다.");
    }
}
