package com.lookable.service.post;

import com.lookable.domain.post.*;
import com.lookable.domain.posttag.PostTag;
import com.lookable.domain.productlink.ProductLink;
import com.lookable.domain.tag.Tag;
import com.lookable.domain.user.User;
import com.lookable.dto.post.request.PostCreateRequest;
import com.lookable.dto.post.request.PostSearchCondition;
import com.lookable.dto.post.request.PostUpdateRequest;
import com.lookable.dto.post.request.ProductLinkRequest;
import com.lookable.dto.post.response.PostDetailResponse;
import com.lookable.dto.post.response.PostThumbnailResponse;
import com.lookable.exception.dto.ErrorCode;
import com.lookable.exception.model.ForbiddenException;
import com.lookable.exception.model.NotFoundException;
import com.lookable.repository.post.PostRepository;
import com.lookable.service.bookmark.BookmarkService;
import com.lookable.service.heart.HeartService;
import com.lookable.service.posttag.PostTagService;
import com.lookable.service.productlink.ProductLinkService;
import com.lookable.service.tag.TagService;
import com.lookable.service.user.UserService;
import com.lookable.service.view.ViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final TagService tagService;
    private final PostTagService postTagService;
    private final ProductLinkService productLinkService;
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
        List<PostTag> postTags = new ArrayList<>();
        tags.forEach(tag -> {
            PostTag postTag = postTagService.getPostTag(post, tag);
            postTags.add(postTag);
        });

        List<ProductLink> productLinks = request.getProductLinks().stream()
                .map(ProductLinkRequest::toEntity)
                .toList();
        productLinks.forEach(productLink -> {
            ProductLink savedProductLink = productLinkService.createProductLink(productLink, post);
            post.getProductLinks().add(savedProductLink);
        });

        postRepository.save(post);
        postTagService.saveAll(postTags);
        post.getPostTags().addAll(postTags);
    }

    @Transactional
    public PostDetailResponse findPostDetail(Long postId, String username) {
        User user = userService.findByUsername(username);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 피드입니다."));
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

    @Transactional
    public PostDetailResponse updatePost(Long postId, PostUpdateRequest request, String username) {
        User user = userService.findByUsername(username);
        Post post = findPost(postId);
        if (!post.getUser().getUsername().equals(username)) {
            throw new ForbiddenException("작성자만 게시글을 수정할 수 있습니다.", ErrorCode.E403_FORBIDDEN_NOT_AUTHOR);
        }

        if (!CollectionUtils.isEmpty(request.getTags())) {
            List<Tag> newTags = request.getTags().stream()
                    .filter(tagName -> post.getPostTags().stream().noneMatch(postTag -> postTag.getTag().getName().equals(tagName)))
                    .map(tagService::findOrCreateTag)
                    .toList();
            List<PostTag> deletedTags = post.getPostTags().stream()
                    .filter(postTag -> request.getTags().stream().noneMatch(tagName -> postTag.getTag().getName().equals(tagName)))
                    .toList();

            deletedTags.forEach(deletedTag -> post.getPostTags().remove(deletedTag));
            postTagService.deletePostTag(deletedTags);

            newTags.forEach(newTag -> {
                PostTag postTag = postTagService.findOrCreatePostTag(post, newTag);
                post.getPostTags().add(postTag);
                newTag.getPostTags().add(postTag);
            });
        }

        if (!CollectionUtils.isEmpty(request.getProductLinks())) {
            List<ProductLink> deletedProductLinks = post.getProductLinks().stream()
                    .filter(productLink -> request.getProductLinks().stream().noneMatch(productLinkRequest -> productLinkRequest.isEqualToProductLink(productLink)))
                    .map(productLink -> productLinkService.createProductLink(productLink, post))
                    .toList();

            List<ProductLink> newProductLinks = request.getProductLinks().stream()
                    .filter(productLinkRequest -> post.getProductLinks().stream().noneMatch(productLinkRequest::isEqualToProductLink))
                    .map(ProductLinkRequest::toEntity)
                    .toList();

            productLinkService.deleteAll(deletedProductLinks);
            post.getProductLinks().removeAll(deletedProductLinks);
            productLinkService.saveAll(newProductLinks);
            post.getProductLinks().addAll(newProductLinks);
        }

        post.updatePost(request.getImg(), request.getDescription(),
                request.getTemperature(), request.getWeather(), request.getSensitivity(),
                request.getCity(), request.getDistrict());
        boolean isHeart = heartService.isHeart(user.getId(), post.getId());
        boolean isBookmark = bookmarkService.isBookmark(user.getId(), post.getId());
        return PostDetailResponse.fromEntity(post, isHeart, isBookmark);
    }

    @Transactional
    public void deletePost(Long postId, String username) {
        Post post = findPost(postId);
        if (!post.getUser().getUsername().equals(username)) {
            throw new ForbiddenException("작성자만 게시글을 삭제할 수 있습니다.", ErrorCode.E403_FORBIDDEN_NOT_AUTHOR);
        }
        postRepository.delete(post);
    }
}
