package com.github.winkathon.lingo.domain.user.dto.response;

import java.util.List;

import com.github.winkathon.lingo.domain.user.schema.User;

import lombok.Builder;

@Builder
public record UserListResponse(

        List<User> users
) {
}
