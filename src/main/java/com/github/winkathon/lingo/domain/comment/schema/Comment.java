package com.github.winkathon.lingo.domain.comment.schema;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.github.winkathon.lingo.common.schema.BaseSchema;
import com.github.winkathon.lingo.domain.post.schema.Post;
import com.github.winkathon.lingo.domain.user.schema.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment extends BaseSchema {

    @DBRef
    private Post post;

    @DBRef
    private Comment comment;

    @DBRef
    private User owner;

    private boolean anonymous;

    private String content;

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Comment target)) {
            return false;
        }

        return this.getId().equals(target.getId());
    }
}
