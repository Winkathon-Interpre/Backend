package com.github.winkathon.team5.domain.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.winkathon.team5.common.api.dto.response.ApiResponse;
import com.github.winkathon.team5.domain.auth.dto.request.LoginRequest;
import com.github.winkathon.team5.domain.auth.dto.request.RefreshRequest;
import com.github.winkathon.team5.domain.auth.dto.request.RegisterRequest;
import com.github.winkathon.team5.domain.auth.dto.response.LoginResponse;
import com.github.winkathon.team5.domain.auth.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request) {

        return ApiResponse.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ApiResponse<Void> register(@RequestBody @Valid RegisterRequest request) {

        authService.register(request);

        return ApiResponse.ok();
    }

    @PostMapping("/refresh")
    public ApiResponse<LoginResponse> refresh(@RequestBody @Valid RefreshRequest request) {

        return ApiResponse.ok(authService.refresh(request));
    }
}
