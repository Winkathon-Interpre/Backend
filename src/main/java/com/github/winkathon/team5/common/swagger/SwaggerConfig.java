package com.github.winkathon.team5.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    public static final String TOKEN = "Access Token";
    private static final String BEARER = "Bearer";

    @Bean()
    public OpenAPI openAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
                .name(TOKEN)
                .type(SecurityScheme.Type.HTTP)
                .scheme(BEARER);

        Components components = new Components()
                .addSecuritySchemes(TOKEN, securityScheme);

        return new OpenAPI().components(components);
    }
}
