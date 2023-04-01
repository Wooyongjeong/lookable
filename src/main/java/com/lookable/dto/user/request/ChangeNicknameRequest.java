package com.lookable.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeNicknameRequest {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

}
