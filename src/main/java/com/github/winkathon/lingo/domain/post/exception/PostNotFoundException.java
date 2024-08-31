package com.github.winkathon.lingo.domain.post.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.lingo.common.api.exception.ApiException;

public class PostNotFoundException extends ApiException {

    public PostNotFoundException() {

        super(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
    }
}
