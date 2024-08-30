package com.github.winkathon.interpre.domain.credit.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.interpre.common.api.exception.ApiException;

public class BuyFailException extends ApiException {

    public BuyFailException() {

        super(HttpStatus.INTERNAL_SERVER_ERROR, "구매에 실패했습니다.");
    }
}
