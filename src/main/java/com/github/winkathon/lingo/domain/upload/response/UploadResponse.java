package com.github.winkathon.lingo.domain.upload.response;

import lombok.Builder;

@Builder
public record UploadResponse(

        String fileName
) {
}
