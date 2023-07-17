package com.lookable.repository.bookmark;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookmarkCustomRepositoryImpl implements BookmarkCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsByUserIdAndPostId(Long userId, Long postId) {
        return false;
    }
}
