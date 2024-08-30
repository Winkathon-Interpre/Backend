package com.github.winkathon.team5.domain.user.dto.response;

import com.github.winkathon.team5.domain.user.schema.User;

import lombok.Builder;

@Builder
public record UserResponse(

        User user
) {
}
