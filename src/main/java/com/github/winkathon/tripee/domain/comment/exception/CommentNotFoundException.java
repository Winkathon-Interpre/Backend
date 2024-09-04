package com.github.winkathon.tripee.domain.comment.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.tripee.common.api.exception.ApiException;

public class CommentNotFoundException extends ApiException {

    public CommentNotFoundException() {

        super(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다.");
    }
}
