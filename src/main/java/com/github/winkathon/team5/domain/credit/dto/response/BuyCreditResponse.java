package com.github.winkathon.team5.domain.credit.dto.response;

import lombok.Builder;

@Builder
public record BuyCreditResponse(

        int addedCredit,
        int totalCredit
) {
}
