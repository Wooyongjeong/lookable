package com.lookable.dto.post.request;

import com.lookable.domain.post.*;
import com.lookable.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class PostCreateRequest {

    private String img;

    @NotBlank(message = "본문을 입력해주세요.")
    private String description;

    @NotBlank(message = "기온 설정을 해주세요.")
    private String temperature;

    @NotBlank(message = "날씨 설정을 해주세요.")
    private String weather;

    @NotBlank(message = "체질 설정을 해주세요.")
    private String sensitivity;

    @NotBlank(message = "주소1을 입력해주세요.")
    private String city;

    @NotBlank(message = "주소2를 입력해주세요.")
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
