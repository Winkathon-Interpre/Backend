package com.github.winkathon.lingo.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.lingo.common.api.exception.ApiException;

public class AlreadyRegisteredPhoneException extends ApiException {

    public AlreadyRegisteredPhoneException() {

        super(HttpStatus.CONFLICT, "이미 등록된 번호입니다.");
    }
}
