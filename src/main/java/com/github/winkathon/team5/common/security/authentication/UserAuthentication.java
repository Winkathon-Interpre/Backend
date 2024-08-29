package com.github.winkathon.team5.common.security.authentication;

import java.util.Collection;
import java.util.stream.Stream;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.github.winkathon.team5.domain.user.schema.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class UserAuthentication implements Authentication {

    private final User user;
    private final String password;

    @Getter
    @Setter
    private boolean authenticated = false;

    public UserAuthentication(User user) {
        this(user, null);
    }

    private Stream<User.Role> getAllRoles(User.Role role) {
        return Stream.concat(
                Stream.of(role),
                Stream.of(role.getInheritedRoles())
                        .flatMap(this::getAllRoles)
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAllRoles(user.role())
                .map(role -> "ROLE_" + role.name())
                .map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getCredentials() {

        return password;
    }

    @Override
    public Object getDetails() {

        return null;
    }

    @Override
    public User getPrincipal() {

        return user;
    }

    @Override
    public String getName() {

        return user.id();
    }
}
