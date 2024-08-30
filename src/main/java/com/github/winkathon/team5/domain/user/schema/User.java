package com.github.winkathon.team5.domain.user.schema;

import com.github.winkathon.team5.common.schema.BaseSchema;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class User extends BaseSchema {

    private String email;
    private String password;

    private Role role;

    @Getter
    public enum Role {

        USER,
        ADMIN(Role.USER),
        ;

        private final Role[] inheritedRoles;

        Role(Role... inheritedRoles) {
            this.inheritedRoles = inheritedRoles;
        }
    }
}
