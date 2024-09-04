package com.github.winkathon.tripee.domain.user.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.tripee.common.api.exception.ApiException;

public class UserNotFoundException extends ApiException {

    public UserNotFoundException() {

        super(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.");
    }
}
