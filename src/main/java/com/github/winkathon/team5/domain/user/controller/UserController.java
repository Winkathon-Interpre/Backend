package com.github.winkathon.team5.domain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.winkathon.team5.common.api.dto.response.ApiResponse;
import com.github.winkathon.team5.common.security.util.UserContext;
import com.github.winkathon.team5.domain.user.dto.request.ChangePasswordRequest;
import com.github.winkathon.team5.domain.user.dto.response.UserListResponse;
import com.github.winkathon.team5.domain.user.schema.User;
import com.github.winkathon.team5.domain.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ApiResponse<UserListResponse> getUserList() {

        return ApiResponse.ok(userService.getUserList());
    }

    @GetMapping("/{userId}")
    public ApiResponse<User> getUserInfo(@PathVariable String userId) {

        return ApiResponse.ok(userService.getUserInfo(userId));
    }

    @PatchMapping("/password")
    public ApiResponse<Void> changePassword(@RequestBody @Valid ChangePasswordRequest request) {

        User user = UserContext.getUser();

        userService.changePassword(user, request);

        return ApiResponse.ok();
    }
}
