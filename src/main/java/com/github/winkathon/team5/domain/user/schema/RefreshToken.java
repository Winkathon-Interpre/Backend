package com.github.winkathon.team5.domain.user.schema;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Builder;

@Builder
@RedisHash(value = "refresh-token", timeToLive = 300)
public record RefreshToken(

        String id,

        @Indexed
        String token,

        @TimeToLive
        long ttl
) {
}
