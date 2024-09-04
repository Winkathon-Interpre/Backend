package com.github.winkathon.tripee.common.security.util;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.github.winkathon.tripee.common.security.authentication.UserAuthentication;
import com.github.winkathon.tripee.domain.user.schema.User;

public class UserContext {

    public static User getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        UserAuthentication authentication = (UserAuthentication)context.getAuthentication();

        return authentication.getPrincipal();
    }
}
