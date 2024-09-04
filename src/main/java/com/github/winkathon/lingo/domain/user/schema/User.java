package com.github.winkathon.lingo.domain.user.schema;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.winkathon.lingo.common.schema.BaseSchema;
import com.github.winkathon.lingo.domain.post.schema.Post;
import com.github.winkathon.lingo.domain.upload.schema.Image;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User extends BaseSchema {

    private String userId;

    @JsonIgnore
    private String password;

    private String name;

    private Image avatar;

    @JsonIgnore
    private List<Post> paidPosts;

    @JsonIgnore
    private List<Post> savedPosts;

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof User user))
            return false;

        return getId().equals(user.getId());
    }
}
