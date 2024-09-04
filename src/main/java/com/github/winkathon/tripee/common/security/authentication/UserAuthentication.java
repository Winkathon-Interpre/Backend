package com.github.winkathon.tripee.common.security.authentication;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.github.winkathon.tripee.domain.user.schema.User;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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

        return user.getId();
    }
}