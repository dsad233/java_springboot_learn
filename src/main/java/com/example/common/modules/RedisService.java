package com.example.common.modules;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@RequiredArgsConstructor
public class RedisService {
    @Autowired(required = true)
    private final RedisTemplate<String, String> redis;

    public String get(String key) {
        return this.redis.opsForValue().get(key);
    }

    public void set(String key, String value) {
        this.redis.opsForValue().set(key, value);
    }

    public void setex(String key, String value, Long seconds) {
        this.redis.opsForValue().set(key, value, seconds);
    }

    public void remove(String key) {
        this.redis.delete(key);
    }
}
