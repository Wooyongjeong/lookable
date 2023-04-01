package com.lookable.dto.auth.response;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class AuthenticationResponse {

    private String token;

}

