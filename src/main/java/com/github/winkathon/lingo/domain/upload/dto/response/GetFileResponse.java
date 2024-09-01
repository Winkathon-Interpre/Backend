package com.github.winkathon.lingo.domain.upload.dto.response;

import com.github.winkathon.lingo.domain.upload.schema.Image;

import lombok.Builder;

@Builder
public record GetFileResponse(

        Image image
) {
}
