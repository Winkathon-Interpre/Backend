package com.github.winkathon.interpre.domain.auth.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(

        String accessToken,
        String refreshToken
) {
}
