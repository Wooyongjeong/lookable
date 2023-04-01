package com.lookable.dto.post.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostThumbnailResponse {

    private Long id;
    private String img;

}
