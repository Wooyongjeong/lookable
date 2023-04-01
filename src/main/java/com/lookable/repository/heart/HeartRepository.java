package com.lookable.repository.heart;

import com.lookable.domain.heart.Heart;
import com.lookable.domain.post.Post;
import com.lookable.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    boolean existsByUserIdAndPostId(Long userId, Long postId);
    Heart findByUserAndPost(User user, Post post);

}
