package com.github.winkathon.team5.domain.user.dto.response;

import java.util.List;

import com.github.winkathon.team5.domain.user.schema.User;

import lombok.Builder;

@Builder
public record UserListResponse(

        List<User> users
) {
}
