package com.lookable.dto.auth.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class AuthenticationResponse {

    private String token;

}

