package com.github.winkathon.interpre.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RefreshRequest(

        @NotBlank
        String token
) {
}