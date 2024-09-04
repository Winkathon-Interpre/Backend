package com.github.winkathon.tripee.domain.upload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.winkathon.tripee.domain.post.exception.ImageNotFoundException;
import com.github.winkathon.tripee.domain.upload.dto.response.GetFileResponse;
import com.github.winkathon.tripee.domain.upload.repository.ImageRepository;
import com.github.winkathon.tripee.domain.upload.response.UploadResponse;
import com.github.winkathon.tripee.domain.upload.schema.Image;
import com.github.winkathon.tripee.domain.upload.util.UploadUtil;
import com.github.winkathon.tripee.domain.user.schema.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UploadService {
    private final UploadUtil uploadUtil;
    private final ImageRepository imageRepository;

    public UploadResponse upload(User user, MultipartFile file) {

        Image upload = uploadUtil.upload(user, file);

        return UploadResponse.builder()
                .image(upload)
                .build();
    }

    public GetFileResponse getFile(String fileId) {

        Image image = imageRepository.findById(fileId)
                .orElseThrow(ImageNotFoundException::new);

        return GetFileResponse.builder()
                .image(image)
                .build();
    }
}
