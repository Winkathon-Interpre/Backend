package com.github.winkathon.team5.domain.user.schema;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Getter;

@Builder
public record User(

        @Id
        String id,

        String email,
        String password,

        Role role
) {

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
