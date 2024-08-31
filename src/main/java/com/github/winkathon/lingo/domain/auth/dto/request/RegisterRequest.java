package com.github.winkathon.lingo.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterRequest(

        @NotBlank
        @Pattern(regexp = "^[가-힣]{2,4}$")
        String name,

        @NotBlank
        @Size(min = 4, max = 128)
        String id,

        @NotBlank
        @Size(min = 4, max = 128)
        String password
) {
}
