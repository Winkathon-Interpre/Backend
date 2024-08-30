package com.github.winkathon.team5.domain.user.schema;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.winkathon.team5.common.schema.BaseSchema;

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

    private LocalDate subscriptionStartDate;

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
