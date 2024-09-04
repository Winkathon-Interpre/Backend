package com.github.winkathon.lingo.domain.post.dto.request;

import com.github.winkathon.lingo.domain.upload.schema.Image;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreatePostRequest(

        @NotBlank
        @Size(min = 10, max = 100)
        String title,

        @NotBlank
        @Size(min = 10, max = 10000)
        String content,

        @NotBlank
        @Size(min = 10, max = 1000)
        String introduce,

        @NotNull
        Boolean isPaid,

        @NotNull
        Integer price,

        @NotNull
        String imageId
) {
}
