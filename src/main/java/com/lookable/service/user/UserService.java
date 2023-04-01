package com.lookable.service.user;

import com.lookable.domain.user.Nickname;
import com.lookable.domain.user.User;
import com.lookable.dto.post.response.PostThumbnailResponse;
import com.lookable.exception.model.DuplicatePasswordException;
import com.lookable.exception.model.NotFoundException;
import com.lookable.repository.post.PostRepository;
import com.lookable.repository.user.NicknameRepository;
import com.lookable.repository.user.UserRepository;
import com.lookable.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final NicknameRepository nicknameRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    public Page<PostThumbnailResponse> findMyPosts(String username, Pageable pageable) {
        Long userId = findByUsername(username).getId();
        return postRepository.findMyPosts(userId, pageable);
    }

    @Transactional(readOnly = true)
    public Page<PostThumbnailResponse> findMyBookmarkPosts(String username, Pageable pageable) {
        Long userId = findByUsername(username).getId();
        return postRepository.findMyBookmarkPosts(userId, pageable);
    }

    @Transactional(readOnly = true)
    public Page<PostThumbnailResponse> findMyHeartPosts(String username, Pageable pageable) {
        Long userId = findByUsername(username).getId();
        return postRepository.findMyHeartPosts(userId, pageable);
    }

    @Transactional
    public void changeNickname(String nickname, String username) {
        authenticationService.checkNickname(nickname);

        User user = findByUsername(username);
        nicknameRepository.delete(user.getNickname());
        Nickname newNickname = Nickname.builder()
                .nickname(nickname)
                .user(user)
                .build();
        user.updateNickname(newNickname);
        nicknameRepository.save(newNickname);
    }

    @Transactional
    public void changePassword(String password, String username) {
        User user = findByUsername(username);

        String encodedPassword = passwordEncoder.encode(password);
        if (user.getPassword().equals(encodedPassword)) {
            throw new DuplicatePasswordException("이전에 사용한 비밀번호입니다.");
        }

        user.updatePassword(encodedPassword);
    }
}
