package com.lookable.repository.heart;

import com.lookable.domain.heart.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
}
