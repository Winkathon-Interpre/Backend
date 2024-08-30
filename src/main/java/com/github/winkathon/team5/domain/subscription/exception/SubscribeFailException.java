package com.github.winkathon.team5.domain.subscription.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.team5.common.api.exception.ApiException;

public class SubscribeFailException extends ApiException {

    public SubscribeFailException() {

        super(HttpStatus.INTERNAL_SERVER_ERROR, "구독에 실패했습니다.");
    }
}
