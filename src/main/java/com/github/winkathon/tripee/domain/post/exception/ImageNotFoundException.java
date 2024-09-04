package com.github.winkathon.tripee.domain.post.exception;

import org.springframework.http.HttpStatus;

import com.github.winkathon.tripee.common.api.exception.ApiException;

public class ImageNotFoundException extends ApiException {

    public ImageNotFoundException() {
        super(HttpStatus.NOT_FOUND,"이미지를 찾을 수 없습니다.");
    }

}
