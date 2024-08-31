package com.github.winkathon.lingo.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(

        @NotBlank
        String oldPassword,

        @NotBlank
        String newPassword
) {
}
