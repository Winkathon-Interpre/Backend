package com.github.winkathon.lingo.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record RegisterRequest(

        @NotBlank
        @Pattern(regexp = "^[가-힣]{2,4}$")
        String name,

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9]{4,}$")
        String id,

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9]{4,}$")
        String password
) {
}
