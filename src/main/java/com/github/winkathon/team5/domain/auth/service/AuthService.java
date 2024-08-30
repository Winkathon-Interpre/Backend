package com.github.winkathon.team5.domain.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.winkathon.team5.common.security.authentication.UserAuthentication;
import com.github.winkathon.team5.common.security.jwt.JwtUtil;
import com.github.winkathon.team5.domain.auth.dto.request.LoginRequest;
import com.github.winkathon.team5.domain.auth.dto.request.RefreshRequest;
import com.github.winkathon.team5.domain.auth.dto.request.RegisterRequest;
import com.github.winkathon.team5.domain.auth.dto.response.LoginResponse;
import com.github.winkathon.team5.domain.auth.exception.AlreadyRegisteredEmailException;
import com.github.winkathon.team5.domain.auth.exception.AuthenticationFailException;
import com.github.winkathon.team5.domain.auth.exception.InvalidRefreshTokenException;
import com.github.winkathon.team5.domain.auth.repository.RefreshTokenRepository;
import com.github.winkathon.team5.domain.auth.schema.RefreshToken;
import com.github.winkathon.team5.domain.user.repository.UserRepository;
import com.github.winkathon.team5.domain.user.schema.User;

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

        String email = dto.email();
        String password = dto.password();

        User user = userRepository.findByEmail(email)
                .orElseThrow(AuthenticationFailException::new);

        UserAuthentication authentication = new UserAuthentication(user, password);
        authenticationManager.authenticate(authentication);

        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void register(RegisterRequest dto) {

        String name = dto.name();
        String email = dto.email();
        String phone = dto.phone();
        String password = dto.password();

        if (userRepository.existsByEmail(email)) {

            throw new AlreadyRegisteredEmailException();
        }

        User user = User.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .password(encoder.encode(password))
                .role(User.Role.USER)
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
}
