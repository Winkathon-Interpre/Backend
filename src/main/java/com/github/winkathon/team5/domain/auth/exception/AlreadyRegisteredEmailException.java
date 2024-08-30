package com.github.winkathon.team5.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.team5.common.api.exception.ApiException;

public class AlreadyRegisteredEmailException extends ApiException {

    public AlreadyRegisteredEmailException() {

        super(HttpStatus.CONFLICT, "이미 등록된 이메일입니다.");
    }
}
