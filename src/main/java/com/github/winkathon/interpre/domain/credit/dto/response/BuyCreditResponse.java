package com.github.winkathon.interpre.domain.credit.dto.response;

import lombok.Builder;

@Builder
public record BuyCreditResponse(

        int addedCredit,
        int totalCredit
) {
}
