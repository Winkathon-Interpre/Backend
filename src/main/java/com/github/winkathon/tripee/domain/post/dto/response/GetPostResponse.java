package com.github.winkathon.tripee.domain.post.dto.response;

import com.github.winkathon.tripee.domain.post.schema.Post;

import lombok.Builder;

@Builder
public record GetPostResponse(

        Post post
) {
}
