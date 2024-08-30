package com.github.winkathon.team5.domain.auth.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(

        String accessToken,
        String refreshToken
) {
}
