package com.github.winkathon.tripee.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(

        @NotBlank
        String oldPassword,

        @NotBlank
        String newPassword
) {
}
