package com.lookable.dto.post.response;

import com.lookable.domain.post.Post;
import com.lookable.domain.productlink.ProductLink;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class PostDetailResponse {

    private Long id;
    private String img;
    private String description;
    private String author;
    private String profileImg;
    private String temperature;
    private String weather;
    private String sensitivity;
    private String city;
    private String district;
    private int heartCount;
    private boolean isHeart;
    private boolean isBookmark;
    private List<String> tags;
    private List<ProductLinkResponse> productLinks;

    public static PostDetailResponse fromEntity(Post post, boolean isHeart, boolean isBookmark) {
        return PostDetailResponse.builder()
                .id(post.getId())
                .img(post.getImg())
                .description(post.getDescription())
                .author(post.getUser().getNickname().getNickname())
                .profileImg(post.getUser().getProfileImg())
                .temperature(post.getFilter().getTemperature().name())
                .weather(post.getFilter().getWeather().name())
                .sensitivity(post.getFilter().getSensitivity().name())
                .city(post.getLocation().getCity())
                .district(post.getLocation().getDistrict())
                .heartCount(post.getHearts().size())
                .isHeart(isHeart)
                .isBookmark(isBookmark)
                .tags(post.getPostTags().stream()
                        .map(postTag -> postTag.getTag().getName())
                        .collect(Collectors.toList()))
                .productLinks(post.getProductLinks().stream()
                        .map(ProductLinkResponse::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }

}
