package com.github.winkathon.tripee.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperty {

    @NotBlank
    private String secretKey;

    @Min(1)
    @Max(8)
    private int accessTokenExpirationsHours;

    @Min(24 * 7)
    @Max(24 * 14)
    private int refreshTokenExpirationsHours;
}
