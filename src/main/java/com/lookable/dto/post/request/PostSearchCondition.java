package com.lookable.dto.post.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class PostSearchCondition {

    @NotBlank(message = "기온 조건을 설정해주세요.")
    private String temperature;

    @NotBlank(message = "날씨 조건을 설정해주세요.")
    private String weather;

    @NotBlank(message = "체질 조건을 설정해주세요.")
    private String sensitivity;

    @NotBlank(message = "시·도 조건을 설정해주세요.")
    private String city;

    @NotBlank(message = "시·군·구 조건을 설정해주세요.")
    private String district;

    @NotNull(message = "page 조건을 설정해주세요.")
    private int page;

    @NotNull(message = "size 조건을 설정해주세요.")
    private int size;

}
