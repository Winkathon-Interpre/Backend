package com.github.winkathon.interpre.domain.user.dto.response;

import java.util.List;

import com.github.winkathon.interpre.domain.user.schema.User;

import lombok.Builder;

@Builder
public record UserListResponse(

        List<User> users
) {
}
