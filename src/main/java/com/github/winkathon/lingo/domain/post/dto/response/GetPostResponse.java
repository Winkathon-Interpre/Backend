package com.github.winkathon.lingo.domain.post.dto.response;

import com.github.winkathon.lingo.domain.post.schema.Post;

import lombok.Builder;

@Builder
public record GetPostResponse(

        Post post
) {
}
