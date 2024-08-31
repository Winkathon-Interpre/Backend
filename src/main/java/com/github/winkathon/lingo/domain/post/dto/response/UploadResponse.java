package com.github.winkathon.lingo.domain.post.dto.response;

import lombok.Builder;

@Builder
public record UploadResponse(

        String fileName
) {
}
