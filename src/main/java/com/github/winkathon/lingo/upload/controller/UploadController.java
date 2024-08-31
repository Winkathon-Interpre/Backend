package com.github.winkathon.lingo.upload.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.winkathon.lingo.common.api.dto.response.ApiResponse;
import com.github.winkathon.lingo.common.security.util.UserContext;
import com.github.winkathon.lingo.domain.user.schema.User;
import com.github.winkathon.lingo.upload.response.UploadResponse;
import com.github.winkathon.lingo.upload.service.UploadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {
    private final UploadService uploadService;
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<UploadResponse> upload(@RequestParam("file") MultipartFile file) {

        User user = UserContext.getUser();
        
        return ApiResponse.ok(uploadService.upload(user, file));
    }
}
