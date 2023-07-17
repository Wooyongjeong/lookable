package com.lookable.service.view;

import com.lookable.domain.post.Post;
import com.lookable.domain.user.User;
import com.lookable.domain.view.View;
import com.lookable.repository.view.ViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ViewService {

    private final ViewRepository viewRepository;

    @Transactional
    public void createViewIfNotPresent(User user, Post post) {
        if (viewRepository.existsByUserAndPost(user, post)) {
            return;
        }
        createView(user, post);
    }

    @Transactional
    public void createView(User user, Post post) {
        View view = View.builder()
                .user(user)
                .post(post)
                .build();
        viewRepository.save(view);
    }

}
