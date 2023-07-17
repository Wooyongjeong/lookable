package com.lookable.controller.user;

import com.lookable.dto.ApiResponse;
import com.lookable.dto.post.response.PostThumbnailResponse;
import com.lookable.dto.user.request.NicknameRequest;
import com.lookable.dto.user.request.PasswordRequest;
import com.lookable.dto.user.request.ChangeProfileImgRequest;
import com.lookable.dto.user.response.UserInfoResponse;
import com.lookable.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UsersApiController {

    private final UserService userService;

    @GetMapping("/me")
    public ApiResponse<UserInfoResponse> getMyInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfoResponse response = userService.findMyInfo(username);
        return ApiResponse.success(response);
    }

    @GetMapping("/me/posts")
    public ApiResponse<Page<PostThumbnailResponse>> getMyPosts(Pageable pageable) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<PostThumbnailResponse> response = userService.findMyPosts(username, pageable);
        return ApiResponse.success(response);
    }

    @GetMapping("/me/bookmarks")
    public ApiResponse<Page<PostThumbnailResponse>> getMyBookmarkPosts(Pageable pageable) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<PostThumbnailResponse> response = userService.findMyBookmarkPosts(username, pageable);
        return ApiResponse.success(response);
    }

    @GetMapping("/me/hearts")
    public ApiResponse<Page<PostThumbnailResponse>> getMyHeartPosts(Pageable pageable) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<PostThumbnailResponse> response = userService.findMyHeartPosts(username, pageable);
        return ApiResponse.success(response);
    }

    @PutMapping("/me/nickname")
    public ApiResponse<String> changeNickname(
            @Valid @RequestBody NicknameRequest request
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.changeNickname(request.getNickname(), username);
        return ApiResponse.OK;
    }

    @PutMapping("/me/password")
    public ApiResponse<String> changePassword(
            @Valid @RequestBody PasswordRequest request
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.changePassword(request.getPassword(), username);
        return ApiResponse.OK;
    }

    @PutMapping("/me/profileImg")
    public ApiResponse<String> changeProfileImg(
            @Valid @RequestBody ChangeProfileImgRequest request
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.changeProfileImg(request.getProfileImg(), username);
        return ApiResponse.OK;
    }

    @DeleteMapping("/me")
    public ApiResponse<String> deleteMe() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.deleteMe(username);
        return ApiResponse.OK;
    }

}
