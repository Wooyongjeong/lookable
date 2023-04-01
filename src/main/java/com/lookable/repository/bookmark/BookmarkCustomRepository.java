package com.lookable.repository.bookmark;

public interface BookmarkCustomRepository {

    boolean existsByIds(Long userId, Long postId);

}
