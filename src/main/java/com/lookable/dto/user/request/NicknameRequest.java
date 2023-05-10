package com.lookable.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class NicknameRequest {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

}
