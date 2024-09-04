package com.github.winkathon.tripee.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.tripee.common.api.exception.ApiException;

public class AccessTokenExpiredException extends ApiException {

    public AccessTokenExpiredException() {

        super(HttpStatus.UNAUTHORIZED, "Access Token이 만료되었습니다.");
    }
}
