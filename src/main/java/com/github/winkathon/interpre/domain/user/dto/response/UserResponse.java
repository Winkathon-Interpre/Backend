package com.github.winkathon.interpre.domain.user.dto.response;

import com.github.winkathon.interpre.domain.user.schema.User;

import lombok.Builder;

@Builder
public record UserResponse(

        User user
) {
}
