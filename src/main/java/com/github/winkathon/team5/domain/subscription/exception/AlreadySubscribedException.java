package com.github.winkathon.team5.domain.subscription.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.team5.common.api.exception.ApiException;

public class AlreadySubscribedException extends ApiException {

    public AlreadySubscribedException() {

        super(HttpStatus.BAD_REQUEST, "이미 구독 중입니다.");
    }
}
