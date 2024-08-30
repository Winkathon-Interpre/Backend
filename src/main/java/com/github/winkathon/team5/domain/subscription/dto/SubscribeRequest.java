package com.github.winkathon.team5.domain.subscription.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SubscribeRequest(

        @NotBlank
        String orderId,

        @NotBlank
        String paymentKey,

        @NotNull
        Integer amount
) {
}
