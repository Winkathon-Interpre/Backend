package com.github.winkathon.lingo.domain.auth.dto.response;

import lombok.Builder;

@Builder
public record PreRegisterResponse(

        boolean exists
) {
}
