package com.lookable.dto.hottopic.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class HotTopicAnswerRequest {

    private String answer;

}
