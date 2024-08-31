package com.github.winkathon.lingo.upload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.winkathon.lingo.domain.user.schema.User;
import com.github.winkathon.lingo.upload.response.UploadResponse;
import com.github.winkathon.lingo.upload.schema.Image;
import com.github.winkathon.lingo.upload.util.UploadUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UploadService {
    private final UploadUtil uploadUtil;

    public UploadResponse upload(User user, MultipartFile file) {

        Image upload = uploadUtil.upload(user, file);

        return UploadResponse.builder()
                .fileName(upload.getFileName())
                .build();
    }
}
