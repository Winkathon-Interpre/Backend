package com.github.winkathon.lingo.domain.auth.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.winkathon.lingo.common.security.authentication.UserAuthentication;
import com.github.winkathon.lingo.common.security.jwt.JwtUtil;
import com.github.winkathon.lingo.domain.auth.dto.request.LoginRequest;
import com.github.winkathon.lingo.domain.auth.dto.request.RefreshRequest;
import com.github.winkathon.lingo.domain.auth.dto.request.RegisterRequest;
import com.github.winkathon.lingo.domain.auth.dto.response.LoginResponse;
import com.github.winkathon.lingo.domain.auth.dto.response.PreRegisterResponse;
import com.github.winkathon.lingo.domain.auth.exception.AuthenticationFailException;
import com.github.winkathon.lingo.domain.auth.exception.ExistsUserIdException;
import com.github.winkathon.lingo.domain.auth.exception.InvalidRefreshTokenException;
import com.github.winkathon.lingo.domain.auth.repository.RefreshTokenRepository;
import com.github.winkathon.lingo.domain.auth.schema.RefreshToken;
import com.github.winkathon.lingo.domain.user.dto.response.UserResponse;
import com.github.winkathon.lingo.domain.user.repository.UserRepository;
import com.github.winkathon.lingo.domain.user.schema.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest dto) {

        String userId = dto.id();
        String password = dto.password();

        User user = userRepository.findByUserId(userId)
                .orElseThrow(AuthenticationFailException::new);

        UserAuthentication authentication = new UserAuthentication(user, password);
        Authentication authenticate = authenticationManager.authenticate(authentication);

        assert authenticate.isAuthenticated();

        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public PreRegisterResponse preRegister(String id) {

        boolean exists = userRepository.existsByUserId(id);

        return PreRegisterResponse.builder()
                .exists(exists)
                .build();
    }

    public void register(RegisterRequest dto) {

        String name = dto.name();
        String userId = dto.id();
        String password = dto.password();

        if (userRepository.existsByUserId(userId)) {

            throw new ExistsUserIdException();
        }

        User user = User.builder()
                .name(name)
                .userId(userId)
                .password(encoder.encode(password))
                .paidPosts(List.of())
                .savedPosts(List.of())
                .build();

        userRepository.save(user);
    }

    public LoginResponse refresh(RefreshRequest dto) {

        String token = dto.token();

        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(token)
                .orElseThrow(InvalidRefreshTokenException::new);

        refreshTokenRepository.delete(refreshToken);

        User user = refreshToken.user();

        String accessToken = jwtUtil.generateAccessToken(user);
        String newRefreshToken = jwtUtil.generateRefreshToken(user);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    public UserResponse me(User user) {

        return UserResponse.builder()
                .user(user)
                .build();
    }
}
