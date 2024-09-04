package com.github.winkathon.tripee.domain.user.dto.response;

import com.github.winkathon.tripee.domain.user.schema.User;

import lombok.Builder;

@Builder
public record UserResponse(

        User user
) {
}
