package com.honeysense.magpie.spring.annotation.cache.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class MagpieRedisHelper {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String namespace, String key, Object value, int maxAge) {
        redisTemplate.opsForHash().put(namespace, key, value);

        if (maxAge > 0) {
            redisTemplate.expire(namespace, maxAge, TimeUnit.SECONDS);
        }

        log.trace(String.format("[put] namespace: %s, key: %s, value: %s, maxAge: %d",
                namespace, key, value, maxAge));
    }

    public void set(String key, Object value, int maxAge) {
        redisTemplate.opsForValue().set(key, value);

        if (maxAge > 0) {
            redisTemplate.expire(key, maxAge, TimeUnit.SECONDS);
        }

        log.trace(String.format("[put] key: %s, value: %s, maxAge: %d",
                key, value, maxAge));
    }

    public Object get(String namespace, String key) {
        Object value = null;

        if (redisTemplate.opsForHash().hasKey(namespace, key)) {
            value = redisTemplate.opsForHash().get(namespace, key);
        }

        if (value == null) {
            log.trace(String.format("[get null] namespace: %s, key: %s",
                    namespace, key));
        } else {
            log.trace(String.format("[get] namespace: %s, key: %s, value: %s",
                    namespace, key, value));
        }

        return value;
    }

    public Object get(String key) {
        Object value = null;

        if (redisTemplate.opsForValue().get(key) != null) {
            value = redisTemplate.opsForValue().get(key);
        }

        if (value == null) {
            log.trace(String.format("[get null] key: %s",
                    key));
        } else {
            log.trace(String.format("[get] key: %s, value: %s",
                    key, value));
        }

        return value;
    }

    public void delete(String namespace, String key) {
        if (redisTemplate.opsForHash().hasKey(namespace, key)) {
            redisTemplate.opsForHash().delete(namespace, key);
        }

        log.trace(String.format("[delete] namespace: %s, key: %s",
                namespace, key));
    }

    public void delete(String namespace) {
        Boolean hasKey = redisTemplate.hasKey(namespace);
        if (hasKey != null && hasKey) {
            redisTemplate.delete(namespace);
        }

        log.trace(String.format("[delete] namespace: %s",
                namespace));
    }
}
