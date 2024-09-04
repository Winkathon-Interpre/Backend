package com.github.winkathon.tripee.domain.upload.dto.response;

import com.github.winkathon.tripee.domain.upload.schema.Image;

import lombok.Builder;

@Builder
public record GetFileResponse(

        Image image
) {
}
