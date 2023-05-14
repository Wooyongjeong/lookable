package com.lookable.dto.user.response;

import com.lookable.domain.user.User;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class UserInfoResponse {

    private String nickname;
    private String profileImg;

    public static UserInfoResponse fromEntity(User user) {
        return UserInfoResponse.builder()
                .nickname(user.getNickname().getNickname())
                .profileImg(user.getProfileImg())
                .build();
    }

}
