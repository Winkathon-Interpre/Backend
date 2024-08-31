package com.github.winkathon.lingo.domain.user.schema;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

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

    @DBRef
    private Image avatar;

    @JsonIgnore
    @DBRef
    private List<Post> paidPosts;

    @JsonIgnore
    @DBRef
    private List<Post> savedPosts;

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof User user))
            return false;

        return getId().equals(user.getId());
    }
}
