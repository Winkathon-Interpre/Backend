package com.github.winkathon.lingo.domain.post.dto.response;

import java.util.List;

import com.github.winkathon.lingo.domain.post.schema.Post;

import lombok.Builder;

@Builder
public record GetPostsResponse(

        List<Post> posts
) {
}
