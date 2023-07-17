package com.lookable.repository.post;

import com.lookable.domain.post.Post;
import com.lookable.domain.user.User;
import com.lookable.dto.post.request.PostSearchCondition;
import com.lookable.dto.post.response.PostDetailResponse;
import com.lookable.dto.post.response.PostThumbnailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface PostCustomRepository {

    Page<PostThumbnailResponse> findPosts(PostSearchCondition request, Pageable pageable);
    Page<Post> findPopularPosts(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    PostDetailResponse findPostDetail1(Long postId, Long userId);
    Post findPostDetail(Long postId);
    Page<PostThumbnailResponse> findMyPosts(Long userId, Pageable pageable);
    Page<PostThumbnailResponse> findMyBookmarkPosts(Long userId, Pageable pageable);
    Page<PostThumbnailResponse> findMyHeartPosts(Long userId, Pageable pageable);

}
