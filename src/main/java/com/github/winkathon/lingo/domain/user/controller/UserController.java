package com.github.winkathon.lingo.domain.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.winkathon.lingo.common.api.dto.response.ApiResponse;
import com.github.winkathon.lingo.common.security.util.UserContext;
import com.github.winkathon.lingo.domain.user.dto.response.UserListResponse;
import com.github.winkathon.lingo.domain.user.dto.response.UserResponse;
import com.github.winkathon.lingo.domain.user.schema.User;
import com.github.winkathon.lingo.domain.user.service.UserService;

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
}
