package com.github.winkathon.lingo.domain.user.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.winkathon.lingo.common.schema.BaseSchema;

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
}
