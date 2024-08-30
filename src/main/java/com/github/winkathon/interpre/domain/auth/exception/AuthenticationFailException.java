package com.github.winkathon.interpre.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.interpre.common.api.exception.ApiException;

public class AuthenticationFailException extends ApiException {

    public AuthenticationFailException() {

        super(HttpStatus.UNAUTHORIZED, "인증에 실패하였습니다.");
    }
}
