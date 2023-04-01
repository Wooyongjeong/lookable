package com.lookable.controller.auth;

import com.lookable.dto.ApiResponse;
import com.lookable.dto.auth.request.AuthenticationRequest;
import com.lookable.dto.auth.request.RegisterRequest;
import com.lookable.dto.auth.response.AuthenticationResponse;
import com.lookable.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthenticationApiController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ApiResponse<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ApiResponse.success(service.register(request));
    }

    @PostMapping("/authenticate")
    public ApiResponse<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        return ApiResponse.success(service.authenticate(request));
    }

    @GetMapping("/check-nickname")
    public ApiResponse<String> checkNickname(@RequestParam String nickname) {
        service.checkNickname(nickname);
        return ApiResponse.success("OK");
    }

}

