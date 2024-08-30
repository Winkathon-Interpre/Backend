package com.github.winkathon.lingo.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisProperty {

    @NotBlank
    private String host;

    @Min(1)
    @Max(65535)
    private int port;

    @Nullable
    private String password;
}
