package com.github.winkathon.tripee.domain.post.dto.response;

import java.util.List;

import com.github.winkathon.tripee.domain.post.schema.Post;

import lombok.Builder;

@Builder
public record GetPostsResponse(

        List<Post> posts
) {
}
