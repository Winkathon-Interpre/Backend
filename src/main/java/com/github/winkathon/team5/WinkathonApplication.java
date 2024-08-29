package com.github.winkathon.team5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.github.winkathon.team5.common.property.MongoProperty;
import com.github.winkathon.team5.common.property.RedisProperty;

@SpringBootApplication
@EnableConfigurationProperties({MongoProperty.class, RedisProperty.class})
public class WinkathonApplication {

    public static void main(String[] args) {

        SpringApplication.run(WinkathonApplication.class, args);
    }
}
