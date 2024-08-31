package com.github.winkathon.lingo.domain.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.winkathon.lingo.domain.upload.schema.Image;
import com.github.winkathon.lingo.domain.upload.util.UploadUtil;
import com.github.winkathon.lingo.domain.user.dto.request.ChangeNameRequest;
import com.github.winkathon.lingo.domain.user.dto.response.UserListResponse;
import com.github.winkathon.lingo.domain.user.dto.response.UserResponse;
import com.github.winkathon.lingo.domain.user.exception.UserNotFoundException;
import com.github.winkathon.lingo.domain.user.repository.UserRepository;
import com.github.winkathon.lingo.domain.user.schema.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UploadUtil uploadUtil;

    public UserListResponse getUserList() {

        List<User> users = userRepository.findAll();

        return UserListResponse.builder()
                .users(users)
                .build();
    }

    public UserResponse getUser(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return UserResponse.builder()
                .user(user)
                .build();
    }

    public void uploadAvatar(User user, MultipartFile file) {

        Image image = uploadUtil.upload(user, file);

        user.setAvatar(image);

        userRepository.save(user);
    }

    public void changeName(User user, ChangeNameRequest dto) {

        String newName = dto.name();

        user.setName(newName);

        userRepository.save(user);
    }
}
