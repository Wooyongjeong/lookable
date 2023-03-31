package com.lookable.repository.posttag;

import com.lookable.domain.posttag.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
}
