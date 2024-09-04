package com.github.winkathon.tripee.domain.user.dto.response;

import java.util.List;

import com.github.winkathon.tripee.domain.user.schema.User;

import lombok.Builder;

@Builder
public record UserListResponse(

        List<User> users
) {
}
