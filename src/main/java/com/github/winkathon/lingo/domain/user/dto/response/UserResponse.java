package com.github.winkathon.lingo.domain.user.dto.response;

import com.github.winkathon.lingo.domain.user.schema.User;

import lombok.Builder;

@Builder
public record UserResponse(

        User user
) {
}
