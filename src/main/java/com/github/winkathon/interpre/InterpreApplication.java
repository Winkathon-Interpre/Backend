package com.github.winkathon.interpre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import com.github.winkathon.interpre.common.property.JwtProperty;
import com.github.winkathon.interpre.common.property.MongoProperty;
import com.github.winkathon.interpre.common.property.RedisProperty;

@SpringBootApplication
@EnableMongoAuditing
@EnableConfigurationProperties({MongoProperty.class, RedisProperty.class, JwtProperty.class})
public class InterpreApplication {

    public static void main(String[] args) {

        SpringApplication.run(InterpreApplication.class, args);
    }
}
