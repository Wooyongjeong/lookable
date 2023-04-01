package com.lookable.service.bookmark;

import com.lookable.domain.bookmark.Bookmark;
import com.lookable.domain.post.Post;
import com.lookable.domain.user.User;
import com.lookable.repository.bookmark.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    @Transactional(readOnly = true)
    public boolean isBookmark(Long userId, Long postId) {
        return bookmarkRepository.existsByUserIdAndPostId(userId, postId);
    }

    @Transactional
    public boolean bookmark(User user, Post post) {
        Bookmark bookmark = bookmarkRepository.findByUserAndPost(user, post);
        if (bookmark == null) {
            createBookmark(user, post);
            return true;
        }
        bookmarkRepository.delete(bookmark);
        return false;
    }

    private void createBookmark(User user, Post post) {
        Bookmark bookmark = Bookmark.builder()
                .user(user)
                .post(post)
                .build();
        bookmarkRepository.save(bookmark);
    }

}
