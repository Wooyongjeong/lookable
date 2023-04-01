package com.lookable.service.posttag;

import com.lookable.domain.post.Post;
import com.lookable.domain.posttag.PostTag;
import com.lookable.domain.tag.Tag;
import com.lookable.repository.posttag.PostTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostTagService {

    private final PostTagRepository postTagRepository;

    @Transactional
    public PostTag createPostTag(Post post, Tag tag) {
        PostTag postTag = PostTag.builder()
                .post(post)
                .tag(tag)
                .build();
        return postTagRepository.save(postTag);
    }

}
