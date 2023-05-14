package com.lookable.dto.post.response;

import com.lookable.domain.post.Post;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class PostThumbnailResponse {

    private Long id;

    private String img;

    private String description;

    private String author;

    private String profileImg;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    public static PostThumbnailResponse fromEntity(Post post) {
        return PostThumbnailResponse.builder()
                .id(post.getId())
                .img(post.getImg())
                .description(post.getDescription())
                .author(post.getUser().getNickname().getNickname())
                .profileImg(post.getUser().getProfileImg())
                .createdAt(post.getCreatedAt())
                .build();
    }

}
