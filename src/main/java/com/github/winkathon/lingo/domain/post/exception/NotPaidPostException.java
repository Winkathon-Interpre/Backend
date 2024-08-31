package com.github.winkathon.lingo.domain.post.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.lingo.common.api.exception.ApiException;

public class NotPaidPostException extends ApiException {

    public NotPaidPostException() {

        super(HttpStatus.FORBIDDEN, "구매하지 않은 게시물입니다.");
    }
}
