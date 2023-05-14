package com.lookable.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ChangeProfileImgRequest {

    @NotBlank(message = "프로필 이미지 링크가 없습니다.")
    private String profileImg;

}
