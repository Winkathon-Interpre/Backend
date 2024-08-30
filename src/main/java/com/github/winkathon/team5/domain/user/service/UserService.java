package com.github.winkathon.team5.domain.user.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.winkathon.team5.common.security.authentication.UserAuthentication;
import com.github.winkathon.team5.domain.user.dto.request.ChangePasswordRequest;
import com.github.winkathon.team5.domain.user.dto.response.UserListResponse;
import com.github.winkathon.team5.domain.user.repository.UserRepository;
import com.github.winkathon.team5.domain.user.schema.User;

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

    public User getUserInfo(String userId) {

        return userRepository.findById(userId)
                .map(user -> {
                    user.setEmail(null);
                    user.setPhone(null);
                    
                    return user;
                })
                .orElse(null);
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
