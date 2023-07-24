package com.lookable.service.heart;

import com.lookable.domain.heart.Heart;
import com.lookable.domain.post.Post;
import com.lookable.domain.user.User;
import com.lookable.repository.heart.HeartRepository;
import com.lookable.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class HeartService {

    private final HeartRepository heartRepository;
    private final RedisService redisService;
    private static final String HEART_COUNT_KEY_PREFIX = "heart:count:";

    @Transactional(readOnly = true)
    public boolean isHeart(Long userId, Long postId) {
        return heartRepository.existsByUserIdAndPostId(userId, postId);
    }

    @Transactional
    public boolean heart(User user, Post post) {
        String key = HEART_COUNT_KEY_PREFIX + post.getId();
        if (redisService.isValueMemberOfSetKey(key, user.getId())) {
            createHeart(user, post);
            redisService.addKeyAndValueToSet(key, user.getId());
            return true;
        }
        Heart heart = heartRepository.findByUserAndPost(user, post);
        heartRepository.delete(heart);
        redisService.removeSetValueFromKey(key, user.getId());
        return false;
    }

    public Integer getHeartCount(Long postId) {
        String key = HEART_COUNT_KEY_PREFIX + postId;
        return redisService.getSetSizeOfKey(key);
    }

    private void createHeart(User user, Post post) {
        Heart heart = Heart.builder()
                .user(user)
                .post(post)
                .build();
        heartRepository.save(heart);
    }
}
