package com.github.winkathon.interpre.domain.credit.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BuyCreditRequest(

        @NotBlank
        String orderId,

        @NotBlank
        String paymentKey,

        @NotNull
        Integer amount
) {
}
