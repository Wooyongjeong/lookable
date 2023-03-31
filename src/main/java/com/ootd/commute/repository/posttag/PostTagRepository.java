package com.ootd.commute.repository.posttag;

import com.ootd.commute.domain.posttag.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
}
