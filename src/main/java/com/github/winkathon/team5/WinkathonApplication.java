package com.github.winkathon.team5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.github.winkathon.team5.common.property.JwtProperty;
import com.github.winkathon.team5.common.property.MongoProperty;
import com.github.winkathon.team5.common.property.RedisProperty;

@SpringBootApplication
@EnableScheduling
@EnableMongoAuditing
@EnableConfigurationProperties({MongoProperty.class, RedisProperty.class, JwtProperty.class})
public class WinkathonApplication {

    public static void main(String[] args) {

        SpringApplication.run(WinkathonApplication.class, args);
    }
}
