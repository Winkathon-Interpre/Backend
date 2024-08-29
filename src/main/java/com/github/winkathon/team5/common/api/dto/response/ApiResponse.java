package com.github.winkathon.team5.common.api.dto.response;

import org.springframework.http.HttpStatus;

import com.github.winkathon.team5.common.api.exception.ApiException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final int statusCode;
    private final String error;
    private final T content;

    public static <T> ApiResponse<T> ok(T content) {

        return new ApiResponse<>(200, null, content);
    }

    public static <T> ApiResponse<T> error(ApiException e) {

        return error(e.getHttpStatus(), e.getMessage());
    }

    public static <T> ApiResponse<T> error(HttpStatus status, String error) {

        return error(status.value(), error);
    }

    public static <T> ApiResponse<T> error(int statusCode, String error) {

        return new ApiResponse<>(statusCode, error, null);
    }
}
