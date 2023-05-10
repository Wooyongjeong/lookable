package com.lookable.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class PasswordRequest {

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

}
