package com.ootd.commute.auth;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class AuthenticationResponse {

    private String token;

}

