package com.ootd.commute.repository.tag;

import com.ootd.commute.domain.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
