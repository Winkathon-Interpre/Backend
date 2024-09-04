package com.github.winkathon.lingo.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.github.winkathon.lingo.common.api.dto.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ApiResponse<?> noResourceFoundException(NoResourceFoundException ignored) {

        return ApiResponse.error(HttpStatus.NOT_FOUND, "요청하신 리소스를 찾을 수 없습니다.");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponse<?> httpMessageNotReadableException(HttpMessageNotReadableException ignored) {

        return ApiResponse.error(HttpStatus.BAD_REQUEST, "Body를 읽을 수 없습니다.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {

        if (e.getBindingResult().getFieldError() == null) {

            return ApiResponse.error(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        String field = e.getBindingResult().getFieldError().getField();
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        String errorMessage = String.format("%s은(는) %s", field, message);

        return ApiResponse.error(HttpStatus.BAD_REQUEST, errorMessage);
    }

    @ExceptionHandler(ApiException.class)
    public ApiResponse<?> apiException(ApiException e) {

        return ApiResponse.error(e.getHttpStatus().value(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> exception(Exception e) {

        log.error("Server Error", e);

        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다.");
    }
}
