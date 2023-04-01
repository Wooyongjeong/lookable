package com.lookable.service.auth;

import com.lookable.dto.auth.request.AuthenticationRequest;
import com.lookable.dto.auth.response.AuthenticationResponse;
import com.lookable.dto.auth.request.RegisterRequest;
import com.lookable.domain.user.Nickname;
import com.lookable.exception.model.DuplicateNicknameException;
import com.lookable.repository.user.NicknameRepository;
import com.lookable.repository.user.UserRepository;
import com.lookable.config.jwt.JwtService;
import com.lookable.domain.user.Role;
import com.lookable.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final NicknameRepository nicknameRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        Nickname nickname = Nickname.builder()
                .nickname(request.getNickname())
                .build();
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .nickname(nickname)
                .role(Role.USER)
                .build();
        userRepository.save(user);
        nickname.setUser(user);
        nicknameRepository.save(nickname);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Transactional(readOnly = true)
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Transactional(readOnly = true)
    public void checkNickname(String nickname) {
        if (nicknameRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException("사용 중인 닉네임입니다.");
        }
    }
}
