package com.github.winkathon.lingo.domain.credit.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.lingo.common.api.exception.ApiException;

public class BuyFailException extends ApiException {

    public BuyFailException() {

        super(HttpStatus.INTERNAL_SERVER_ERROR, "구매에 실패했습니다.");
    }
}
