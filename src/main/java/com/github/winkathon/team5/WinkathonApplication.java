package com.github.winkathon.team5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.github.winkathon.team5.common.property.MongoProperty;
import com.github.winkathon.team5.common.property.RedisProperty;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableConfigurationProperties({MongoProperty.class, RedisProperty.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Winkathon Team 5",
                description = "2024년도 국민대학교 소프트웨어융합대학 웹 학술 동아리 Wink 해커톤 - 5팀"
        )
)
public class WinkathonApplication {

    public static void main(String[] args) {

        SpringApplication.run(WinkathonApplication.class, args);
    }
}
