package com.github.winkathon.tripee.domain.upload.response;

import com.github.winkathon.tripee.domain.upload.schema.Image;

import lombok.Builder;

@Builder
public record UploadResponse(

        Image image
) {
}
