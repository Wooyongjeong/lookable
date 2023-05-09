package com.lookable.domain.hottopicanswer;

import com.lookable.domain.BaseEntity;
import com.lookable.domain.hottopic.HotTopic;
import com.lookable.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class HotTopicAnswer extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hot_topic_id")
    private HotTopic hotTopic;

    @Enumerated(EnumType.STRING)
    private Answer answer;

}
