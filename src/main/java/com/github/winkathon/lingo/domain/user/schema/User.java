package com.github.winkathon.lingo.domain.user.schema;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.winkathon.lingo.common.schema.BaseSchema;
import com.github.winkathon.lingo.domain.post.schema.Post;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class User extends BaseSchema {

    private String userId;

    @JsonIgnore
    private String password;

    private String name;

    private List<Post> paidPosts;
    private List<Post> savedPosts;
}
