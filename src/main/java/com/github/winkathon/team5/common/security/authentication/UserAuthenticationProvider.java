package com.github.winkathon.team5.common.security.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.winkathon.team5.domain.auth.exception.AuthenticationFailException;

@Configuration
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        UserAuthentication userAuthentication = (UserAuthentication)authentication;

        if (userAuthentication.getPrincipal() == null || userAuthentication.getCredentials() == null) {
            throw new AuthenticationFailException();
        }

        if (!passwordEncoder().matches(userAuthentication.getCredentials(),
                userAuthentication.getPrincipal().password())) {
            throw new AuthenticationFailException();
        }

        userAuthentication.setAuthenticated(true);

        return userAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UserAuthentication.class);
    }
}
