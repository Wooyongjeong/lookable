package com.lookable.repository.bookmark;

public interface BookmarkCustomRepository {

    boolean existsByUserIdAndPostId(Long userId, Long postId);

}
