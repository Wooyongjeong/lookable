package com.lookable.controller.post;

import com.lookable.dto.ApiResponse;
import com.lookable.dto.post.request.PostCreateRequest;
import com.lookable.dto.post.request.PostSearchCondition;
import com.lookable.dto.post.response.PostDetailResponse;
import com.lookable.dto.post.response.PostThumbnailResponse;
import com.lookable.service.post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@RestController
public class PostsApiController {

    private final PostService postService;

    @GetMapping
    public ApiResponse<Page<PostThumbnailResponse>> getPosts(
            @Valid @RequestBody PostSearchCondition condition,
            Pageable pageable
    ) {
        Page<PostThumbnailResponse> response = postService.findPosts(condition, pageable);
        return ApiResponse.success(response);
    }

    @PostMapping("/new")
    public ApiResponse<String> createPost(
            @Valid @RequestBody PostCreateRequest request
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        postService.createPost(request, username);
        return ApiResponse.OK;
    }

    @GetMapping("/{postId}")
    public ApiResponse<PostDetailResponse> getPostDetail(
            @PathVariable Long postId
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        PostDetailResponse response = postService.findPostDetail(postId, username);
        return ApiResponse.success(response);
    }

    @PostMapping("/{postId}/heart")
    public ApiResponse<String> heart(
            @PathVariable Long postId
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String response = postService.heartPost(postId, username);
        return ApiResponse.success(response);
    }

    @PostMapping("/{postId}/bookmark")
    public ApiResponse<String> bookmark(
            @PathVariable Long postId
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String response = postService.bookmarkPost(postId, username);
        return ApiResponse.success(response);
    }

}
