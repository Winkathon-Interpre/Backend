package com.github.winkathon.lingo.domain.upload.response;

import com.github.winkathon.lingo.domain.upload.schema.Image;

import lombok.Builder;

@Builder
public record UploadResponse(

        Image image
) {
}
