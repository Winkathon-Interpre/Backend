package com.github.winkathon.tripee.domain.post.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.tripee.common.api.exception.ApiException;

public class NotOwnPostException extends ApiException {

    public NotOwnPostException() {

        super(HttpStatus.FORBIDDEN, "소유하지 않은 게시물입니다.");
    }
}
