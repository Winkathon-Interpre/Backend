package com.github.winkathon.lingo.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginRequest(

        @NotBlank
        String id,

        @NotBlank
        String password
) {
}
