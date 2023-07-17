package com.lookable.controller.post;

import com.lookable.dto.ApiResponse;
import com.lookable.dto.post.request.PostCreateRequest;
import com.lookable.dto.post.request.PostSearchCondition;
import com.lookable.dto.post.request.PostUpdateRequest;
import com.lookable.dto.post.response.PostDetailResponse;
import com.lookable.dto.post.response.PostThumbnailResponse;
import com.lookable.service.post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
            @Valid @ModelAttribute PostSearchCondition condition
    ) {
        Pageable pageable = PageRequest.of(condition.getPage(), condition.getSize());
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

    @PutMapping("/{postId}")
    public ApiResponse<PostDetailResponse> updatePost(
            @PathVariable Long postId,
            @RequestBody PostUpdateRequest request
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        PostDetailResponse response = postService.updatePost(postId, request, username);
        return ApiResponse.success(response);
    }

    @DeleteMapping("/{postId}")
    public ApiResponse<String> deletePost(
            @PathVariable Long postId
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        postService.deletePost(postId, username);
        return ApiResponse.OK;
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
