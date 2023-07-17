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

    public PostTag getPostTag(Post post, Tag tag) {
        return PostTag.builder()
                .post(post)
                .tag(tag)
                .build();
    }

    @Transactional
    public PostTag findOrCreatePostTag(Post post, Tag tag) {
        return postTagRepository.findByPostAndTag(post, tag)
                .orElseGet(() -> getPostTag(post, tag));
    }

    @Transactional
    public PostTag createPostTag(Post post, Tag tag) {
        return postTagRepository.save(getPostTag(post, tag));
    }

    @Transactional
    public PostTag savePostTag(PostTag postTag) {
        return postTagRepository.save(postTag);
    }

    @Transactional
    public List<PostTag> saveAll(List<PostTag> postTags) {
        return postTagRepository.saveAll(postTags);
    }

    @Transactional
    public void deletePostTag(List<PostTag> postTags) {
        postTagRepository.deleteAll(postTags);
    }

}
