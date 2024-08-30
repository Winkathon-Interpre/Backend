package com.github.winkathon.interpre.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record RegisterRequest(

        @NotBlank
        @Pattern(regexp = "^[가-힣]{2,4}$")
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")
        String phone,

        @NotBlank
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&#.~_-])[A-Za-z\\d@$!%*?&#.~_-]{8,}$")
        String password
) {
}
