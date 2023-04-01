package com.lookable.repository.bookmark;

import com.lookable.domain.bookmark.Bookmark;
import com.lookable.domain.post.Post;
import com.lookable.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>, BookmarkCustomRepository {

    boolean existsByUserIdAndPostId(Long userId, Long postId);
    Bookmark findByUserAndPost(User user, Post post);

}
