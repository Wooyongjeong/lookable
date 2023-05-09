package com.lookable.repository.hottopic;

import com.lookable.domain.hottopic.HotTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotTopicRepository extends JpaRepository<HotTopic, Long> {

    List<HotTopic> findByTemperatureFromLessThanEqualAndTemperatureToGreaterThanEqual(int temperatureFrom, int temperatureTo);

}
