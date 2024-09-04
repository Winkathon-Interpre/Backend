package com.github.winkathon.tripee.common.security.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.github.winkathon.tripee.common.property.JwtProperty;
import com.github.winkathon.tripee.domain.auth.repository.RefreshTokenRepository;
import com.github.winkathon.tripee.domain.auth.schema.RefreshToken;
import com.github.winkathon.tripee.domain.user.schema.User;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperty jwtProperty;
    private final RefreshTokenRepository refreshTokenRepository;

    private Algorithm algorithm;

    @PostConstruct
    public void init() {

        algorithm = Algorithm.HMAC256(jwtProperty.getSecretKey());
    }

    public String generateAccessToken(User user) {

        return JWT.create()
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(jwtProperty.getAccessTokenExpirationsHours(), ChronoUnit.HOURS))
                .withClaim("id", user.getId())
                .sign(algorithm);
    }

    public String generateRefreshToken(User user) {

        String token = JWT.create()
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(jwtProperty.getRefreshTokenExpirationsHours(), ChronoUnit.HOURS))
                .sign(algorithm);

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(token)
                .ttl(jwtProperty.getRefreshTokenExpirationsHours())
                .build();

        refreshTokenRepository.save(refreshToken);

        return token;
    }

    public String extractToken(String token) {

        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getClaim("id")
                .asString();
    }

    public boolean validateToken(String token) throws TokenExpiredException {

        if (token == null) {

            return false;
        }

        JWT.require(algorithm).build().verify(token);

        return true;
    }
}
