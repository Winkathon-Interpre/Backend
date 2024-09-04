package com.github.winkathon.tripee.domain.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BuyPostRequest(

        @NotBlank
        String orderId,

        @NotBlank
        String paymentKey,

        @NotNull
        Integer amount
) {
}
