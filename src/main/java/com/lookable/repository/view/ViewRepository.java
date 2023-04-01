package com.lookable.repository.view;

import com.lookable.domain.post.Post;
import com.lookable.domain.user.User;
import com.lookable.domain.view.View;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewRepository extends JpaRepository<View, Long> {

    boolean existsByUserIdAndPostId(Long userId, Long postId);
    View findByUserAndPost(User user, Post post);

}
