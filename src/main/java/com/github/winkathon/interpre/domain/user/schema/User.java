package com.github.winkathon.interpre.domain.user.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.winkathon.interpre.common.schema.BaseSchema;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class User extends BaseSchema {

    private String name;
    private String email;
    private String phone;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Role role;

    private int credit;

    @Getter
    public enum Role {

        USER,
        ;

        private final Role[] inheritedRoles;

        Role(Role... inheritedRoles) {
            this.inheritedRoles = inheritedRoles;
        }
    }
}
