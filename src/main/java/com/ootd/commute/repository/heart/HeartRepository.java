package com.ootd.commute.repository.heart;

import com.ootd.commute.domain.heart.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
}
