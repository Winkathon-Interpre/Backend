package com.github.winkathon.interpre.domain.auth.schema;

import java.util.concurrent.TimeUnit;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import com.github.winkathon.interpre.domain.user.schema.User;

import lombok.Builder;

@Builder
@RedisHash(value = "refresh-token", timeToLive = 300)
public record RefreshToken(

        @Id
        String token,

        User user,

        @TimeToLive(unit = TimeUnit.HOURS)
        long ttl
) {
}
