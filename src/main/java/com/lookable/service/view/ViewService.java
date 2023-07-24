package com.lookable.service.view;

import com.lookable.domain.post.Post;
import com.lookable.domain.user.User;
import com.lookable.domain.view.View;
import com.lookable.repository.view.ViewRepository;
import com.lookable.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ViewService {

    private final ViewRepository viewRepository;
    private final RedisService redisService;
    private static final String VIEW_COUNT_KEY_PREFIX = "view:count:";

    @Transactional
    public void createViewIfNotPresent(User user, Post post) {
        String key = VIEW_COUNT_KEY_PREFIX + post.getId();
        if (redisService.isValueMemberOfSetKey(key, user.getId())) {
            return;
        }
        createView(user, post);
        redisService.addKeyAndValueToSet(key, user.getId());
    }

    public Integer getViewCount(Long postId) {
        String key = VIEW_COUNT_KEY_PREFIX + postId;
        return redisService.getSetSizeOfKey(key);
    }

    @Transactional
    public void createView(User user, Post post) {
        View view = View.builder()
                .user(user)
                .post(post)
                .build();
        viewRepository.save(view);
    }

}
