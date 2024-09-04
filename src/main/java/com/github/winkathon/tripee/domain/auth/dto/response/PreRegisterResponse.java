package com.github.winkathon.tripee.domain.auth.dto.response;

import lombok.Builder;

@Builder
public record PreRegisterResponse(

        boolean exists
) {
}
