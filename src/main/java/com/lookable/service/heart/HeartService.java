package com.lookable.service.heart;

import com.lookable.domain.heart.Heart;
import com.lookable.domain.post.Post;
import com.lookable.domain.user.User;
import com.lookable.repository.heart.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class HeartService {

    private final HeartRepository heartRepository;

    @Transactional(readOnly = true)
    public boolean isHeart(Long userId, Long postId) {
        return heartRepository.existsByUserIdAndPostId(userId, postId);
    }

    @Transactional
    public boolean heart(User user, Post post) {
        Heart heart = heartRepository.findByUserAndPost(user, post);
        if (heart == null) {
            createHeart(user, post);
            return true;
        }
        heartRepository.delete(heart);
        return false;
    }

    private void createHeart(User user, Post post) {
        Heart heart = Heart.builder()
                .user(user)
                .post(post)
                .build();
        heartRepository.save(heart);
    }
}
