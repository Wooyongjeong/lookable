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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createViewIfNotPresent(User user, Post post) {
        if (viewRepository.existsByUserIdAndPostId(user.getId(), post.getId())) {
            return;
        }
        createView(user, post);
    }

    private void createView(User user, Post post) {
        View view = View.builder()
                .user(user)
                .post(post)
                .build();
        viewRepository.save(view);
    }

}
