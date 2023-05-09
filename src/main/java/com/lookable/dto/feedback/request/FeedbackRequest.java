package com.lookable.dto.feedback.request;

import com.lookable.domain.feedback.Feedback;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class FeedbackRequest {

    @Email(message = "잘못된 이메일 형식입니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @Size(message = "1000자 이하로 입력해주세요.", max = 1000)
    @NotBlank(message = "피드백 내용을 입력해주세요.")
    private String content;

    public Feedback toEntity() {
        return Feedback.builder()
                .email(email)
                .content(content)
                .build();
    }

}
