package com.lookable.service.user;

import com.lookable.domain.user.User;
import com.lookable.exception.model.DuplicateNicknameException;
import com.lookable.exception.model.NotFoundException;
import com.lookable.repository.user.NicknameRepository;
import com.lookable.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final NicknameRepository nicknameRepository;

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));
    }

}
