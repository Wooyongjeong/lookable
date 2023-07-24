package com.lookable.service.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisService {

    private final RedisTemplate<String, Long> redisTemplate;

    public void addKeyAndValueToSet(String key, Long value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public int getSetSizeOfKey(String key) {
        Long size = redisTemplate.opsForSet().size(key);
        return null != size ? size.intValue() : 0;
    }

    public boolean isValueMemberOfSetKey(String key, Long value) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, value));
    }

    public void removeSetValueFromKey(String key, Long value) {
        redisTemplate.opsForSet().remove(key, value);
    }

}
