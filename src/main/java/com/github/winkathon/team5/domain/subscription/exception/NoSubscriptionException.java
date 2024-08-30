package com.github.winkathon.team5.domain.subscription.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.team5.common.api.exception.ApiException;

public class NoSubscriptionException extends ApiException {

    public NoSubscriptionException() {

        super(HttpStatus.BAD_REQUEST, "구독 정보가 없습니다.");
    }
}
