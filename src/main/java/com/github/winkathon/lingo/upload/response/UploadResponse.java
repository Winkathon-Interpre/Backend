package com.github.winkathon.lingo.upload.response;

import lombok.Builder;

@Builder
public record UploadResponse(

        String fileName
) {
}
