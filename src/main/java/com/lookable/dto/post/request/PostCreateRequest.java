package com.lookable.dto.post.request;

import com.lookable.domain.post.*;
import com.lookable.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreateRequest {

    @NotBlank(message = "")
    private String img;

    @NotBlank(message = "")
    private String description;

    @NotBlank(message = "")
    private String temperature;

    @NotBlank(message = "")
    private String weather;

    @NotBlank(message = "")
    private String sensitivity;

    @NotBlank(message = "")
    private String city;

    @NotBlank(message = "")
    private String district;

    private List<String> tags;

    private List<ProductLinkRequest> productLinks;

    public Post toEntity(User user) {
        PostFilter filter = PostFilter.builder()
                .temperature(Temperature.valueOf(temperature))
                .weather(Weather.valueOf(weather))
                .sensitivity(Sensitivity.valueOf(sensitivity))
                .build();
        Location location = Location.builder()
                .city(city)
                .district(district)
                .build();
        return Post.builder()
                .user(user)
                .img(img)
                .description(description)
                .filter(filter)
                .location(location)
                .build();
    }
}
