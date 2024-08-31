package com.github.winkathon.lingo.domain.comment.dto.response;

import java.util.List;

import com.github.winkathon.lingo.domain.comment.schema.Comment;

import lombok.Builder;

@Builder
public record GetCommentsResponse(

        List<Comment> comments
) {
}
