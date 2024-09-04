package com.github.winkathon.tripee.domain.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.winkathon.tripee.common.api.dto.response.ApiResponse;
import com.github.winkathon.tripee.common.security.util.UserContext;
import com.github.winkathon.tripee.domain.user.dto.request.ChangeNameRequest;
import com.github.winkathon.tripee.domain.user.dto.request.ChangePasswordRequest;
import com.github.winkathon.tripee.domain.user.dto.response.UserListResponse;
import com.github.winkathon.tripee.domain.user.dto.response.UserResponse;
import com.github.winkathon.tripee.domain.user.schema.User;
import com.github.winkathon.tripee.domain.user.service.UserService;

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

    @PutMapping("/avatar")
    public ApiResponse<Void> uploadAvatar(MultipartFile file) {

        User user = UserContext.getUser();

        userService.uploadAvatar(user, file);

        return ApiResponse.ok();
    }

    // a
    @PutMapping("/name")
    public ApiResponse<Void> changeName(@RequestBody ChangeNameRequest request) {

        User user = UserContext.getUser();

        userService.changeName(user, request);

        return ApiResponse.ok();
    }

    @PutMapping("/password")
    public ApiResponse<Void> changePassword(@RequestBody ChangePasswordRequest request) {

        User user = UserContext.getUser();

        userService.changePassword(user, request);

        return ApiResponse.ok();
    }
}
