package com.lookable.repository.posttag;

import com.lookable.domain.post.Post;
import com.lookable.domain.posttag.PostTag;
import com.lookable.domain.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
    Optional<PostTag> findByPostAndTag(Post post, Tag tag);
}
