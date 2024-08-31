package com.github.winkathon.lingo.domain.comment.schema;

import com.github.winkathon.lingo.common.schema.BaseSchema;
import com.github.winkathon.lingo.domain.post.schema.Post;
import com.github.winkathon.lingo.domain.user.schema.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Comment extends BaseSchema {

    private Post post;
    private Comment comment;
    private User owner;

    private boolean anonymous;

    private String content;
}
