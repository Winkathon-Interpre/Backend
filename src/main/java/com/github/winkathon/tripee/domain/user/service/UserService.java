package com.github.winkathon.tripee.domain.user.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.winkathon.tripee.common.security.authentication.UserAuthentication;
import com.github.winkathon.tripee.domain.upload.schema.Image;
import com.github.winkathon.tripee.domain.upload.util.UploadUtil;
import com.github.winkathon.tripee.domain.user.dto.request.ChangeNameRequest;
import com.github.winkathon.tripee.domain.user.dto.request.ChangePasswordRequest;
import com.github.winkathon.tripee.domain.user.dto.response.UserListResponse;
import com.github.winkathon.tripee.domain.user.dto.response.UserResponse;
import com.github.winkathon.tripee.domain.user.exception.UserNotFoundException;
import com.github.winkathon.tripee.domain.user.repository.UserRepository;
import com.github.winkathon.tripee.domain.user.schema.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UploadUtil uploadUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

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

    public void changePassword(User user, ChangePasswordRequest dto) {

        String oldPassword = dto.oldPassword();
        String newPassword = dto.newPassword();

        UserAuthentication authentication = new UserAuthentication(user, oldPassword);
        Authentication authenticate = authenticationManager.authenticate(authentication);

        assert authenticate.isAuthenticated();

        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
    }
}
