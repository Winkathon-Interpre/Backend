package com.github.winkathon.tripee.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCommentRequest(

        @NotBlank
        @Size(max = 1000)
        String content,

        @NotNull
        Boolean anonymous
) {
}
