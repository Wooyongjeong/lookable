package com.lookable.dto.post.request;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class PostUpdateRequest {

    private String img;

    private String description;

    private String temperature;

    private String weather;

    private String sensitivity;

    private String city;

    private String district;

    private List<String> tags;

    private List<ProductLinkRequest> productLinks;

}
