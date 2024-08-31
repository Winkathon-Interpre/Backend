package com.github.winkathon.lingo.domain.post.schema;

import com.github.winkathon.lingo.common.schema.BaseSchema;
import com.github.winkathon.lingo.domain.user.schema.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post extends BaseSchema {

    private User owner;

    private String title;
    private String content;

    private boolean paid;
    private int price;

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Post post))
            return false;

        return getId().equals(post.getId());
    }
}
