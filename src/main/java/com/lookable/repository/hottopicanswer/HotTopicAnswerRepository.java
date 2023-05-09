package com.lookable.repository.hottopicanswer;

import com.lookable.domain.hottopic.HotTopic;
import com.lookable.domain.hottopicanswer.Answer;
import com.lookable.domain.hottopicanswer.HotTopicAnswer;
import com.lookable.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotTopicAnswerRepository extends JpaRepository<HotTopicAnswer, Long> {

    boolean existsByHotTopicAndUser(HotTopic hotTopic, User user);

    Integer countByHotTopicAndAnswer(HotTopic hotTopic, Answer answer);

}
