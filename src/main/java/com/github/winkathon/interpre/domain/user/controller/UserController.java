package com.github.winkathon.interpre.domain.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.winkathon.interpre.common.api.dto.response.ApiResponse;
import com.github.winkathon.interpre.common.security.util.UserContext;
import com.github.winkathon.interpre.domain.user.dto.request.ChangePasswordRequest;
import com.github.winkathon.interpre.domain.user.dto.response.UserListResponse;
import com.github.winkathon.interpre.domain.user.dto.response.UserResponse;
import com.github.winkathon.interpre.domain.user.schema.User;
import com.github.winkathon.interpre.domain.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    @PreAuthorize("permitAll()")
    public ApiResponse<UserListResponse> getUserList() {

        return ApiResponse.ok(userService.getUserList());
    }

    @GetMapping("/{userId}")
    @PreAuthorize("permitAll()")
    public ApiResponse<UserResponse> getUser(@PathVariable String userId) {

        return ApiResponse.ok(userService.getUser(userId));
    }

    @PatchMapping("/password")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Void> changePassword(@RequestBody @Valid ChangePasswordRequest request) {

        User user = UserContext.getUser();

        userService.changePassword(user, request);

        return ApiResponse.ok();
    }
}
