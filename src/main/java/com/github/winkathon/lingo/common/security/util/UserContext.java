package com.github.winkathon.lingo.common.security.util;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.github.winkathon.lingo.common.security.authentication.UserAuthentication;
import com.github.winkathon.lingo.domain.user.schema.User;

public class UserContext {

    public static User getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        UserAuthentication authentication = (UserAuthentication)context.getAuthentication();

        return authentication.getPrincipal();
    }
}
