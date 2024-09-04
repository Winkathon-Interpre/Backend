package com.github.winkathon.lingo.domain.post.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.lingo.common.api.exception.ApiException;

public class ImageNotFoundException extends ApiException {

    public ImageNotFoundException() {
        super(HttpStatus.NOT_FOUND,"이미지를 찾을 수 없습니다.");
    }
    
}
