package com.github.winkathon.team5.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.team5.common.api.exception.ApiException;

public class InvalidRefreshTokenException extends ApiException {

    public InvalidRefreshTokenException() {

        super(HttpStatus.UNAUTHORIZED, "리프레시 토큰이 유효하지 않습니다.");
    }
}
