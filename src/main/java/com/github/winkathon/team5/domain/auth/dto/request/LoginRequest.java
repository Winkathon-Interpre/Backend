package com.github.winkathon.team5.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginRequest(

        @NotBlank
        String email,

        @NotBlank
        String password
) {
}
