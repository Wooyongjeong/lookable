package com.lookable.dto.hottopic.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class HotTopicQuestionResponse {

    private Long id;

    private String question;

    private HotTopicAnswerResponse answer;



}
