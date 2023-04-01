package com.lookable.service.post;

import com.lookable.domain.post.Post;
import com.lookable.domain.posttag.PostTag;
import com.lookable.domain.tag.Tag;
import com.lookable.domain.user.User;
import com.lookable.dto.post.request.PostCreateRequest;
import com.lookable.dto.post.request.PostSearchCondition;
import com.lookable.dto.post.response.PostDetailResponse;
import com.lookable.dto.post.response.PostThumbnailResponse;
import com.lookable.exception.model.NotFoundException;
import com.lookable.repository.post.PostRepository;
import com.lookable.service.bookmark.BookmarkService;
import com.lookable.service.heart.HeartService;
import com.lookable.service.posttag.PostTagService;
import com.lookable.service.tag.TagService;
import com.lookable.service.user.UserService;
import com.lookable.service.view.ViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final TagService tagService;
    private final PostTagService postTagService;
    private final BookmarkService bookmarkService;
    private final HeartService heartService;
    private final ViewService viewService;

    @Transactional(readOnly = true)
    public Page<PostThumbnailResponse> findPosts(PostSearchCondition condition, Pageable pageable) {
        return postRepository.findPosts(condition, pageable);
    }

    @Transactional
    public void createPost(PostCreateRequest request, String username) {
        User user = userService.findByUsername(username);
        Post post = request.toEntity(user);
        List<Tag> tags = tagService.findOrCreateTags(request.getTags());
        tags.forEach(tag -> {
            PostTag postTag = postTagService.createPostTag(post, tag);
            post.getPostTags().add(postTag);
            tag.getPostTags().add(postTag);
        });
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public PostDetailResponse findPostDetail(Long postId, String username) {
        User user = userService.findByUsername(username);
        Post post = postRepository.findPostDetail(postId, user);
        if (post == null) {
            throw new NotFoundException("존재하지 않는 피드입니다.");
        }
        boolean isHeart = heartService.isHeart(user.getId(), post.getId());
        boolean isBookmark = bookmarkService.isBookmark(user.getId(), post.getId());
        viewService.createViewIfNotPresent(user, post);
        return PostDetailResponse.fromEntity(post, isHeart, isBookmark);
    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 피드입니다."));
    }

    @Transactional
    public String heartPost(Long postId, String username) {
        User user = userService.findByUsername(username);
        Post post = findPost(postId);
        return heartService.heart(user, post) ? "좋아요" : "좋아요 취소";
    }

    @Transactional
    public String bookmarkPost(Long postId, String username) {
        User user = userService.findByUsername(username);
        Post post = findPost(postId);
        return bookmarkService.bookmark(user, post) ? "북마크" : "북마크 취소";
    }

}
