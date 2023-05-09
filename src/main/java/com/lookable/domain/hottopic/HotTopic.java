package com.lookable.domain.hottopic;

import com.lookable.domain.BaseEntity;
import com.lookable.domain.hottopicanswer.HotTopicAnswer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class HotTopic extends BaseEntity {

    @Column(nullable = false)
    private String question;

    private Integer temperatureFrom;

    private Integer temperatureTo;

    @Builder.Default
    @OneToMany(mappedBy = "hotTopic", fetch = FetchType.LAZY)
    private List<HotTopicAnswer> hotTopicAnswers = new ArrayList<>();

}
