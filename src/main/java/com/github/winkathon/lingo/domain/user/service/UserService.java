package com.github.winkathon.lingo.domain.user.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.winkathon.lingo.common.security.authentication.UserAuthentication;
import com.github.winkathon.lingo.domain.user.dto.request.ChangePasswordRequest;
import com.github.winkathon.lingo.domain.user.dto.response.UserListResponse;
import com.github.winkathon.lingo.domain.user.dto.response.UserResponse;
import com.github.winkathon.lingo.domain.user.repository.UserRepository;
import com.github.winkathon.lingo.domain.user.schema.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserListResponse getUserList() {

        List<User> users = userRepository.findAll().stream()
                .peek(user -> user.setEmail(null))
                .peek(user -> user.setPhone(null))
                .toList();

        return UserListResponse.builder()
                .users(users)
                .build();
    }

    public UserResponse getUser(String userId) {

        User user = userRepository.findById(userId)
                .map(user1 -> {
                    user1.setEmail(null);
                    user1.setPhone(null);

                    return user1;
                })
                .orElse(null);

        return UserResponse.builder()
                .user(user)
                .build();
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
