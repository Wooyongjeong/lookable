package com.lookable.controller.hottopic;

import com.lookable.dto.ApiResponse;
import com.lookable.dto.hottopic.request.HotTopicAnswerRequest;
import com.lookable.dto.hottopic.request.HotTopicQuestionRequest;
import com.lookable.dto.hottopic.response.HotTopicAnswerResponse;
import com.lookable.dto.hottopic.response.HotTopicQuestionResponse;
import com.lookable.service.hottopic.HotTopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/hottopic")
@RestController
public class HotTopicApiController {

    private final HotTopicService hotTopicService;

    @GetMapping("/question")
    public ApiResponse<HotTopicQuestionResponse> question(
            @Valid @ModelAttribute HotTopicQuestionRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        HotTopicQuestionResponse response = hotTopicService.getQuestion(username, request);
        return ApiResponse.success(response);
    }

    @PostMapping("/{hotTopicId}")
    public ApiResponse<HotTopicAnswerResponse> answer(
            @PathVariable Long hotTopicId,
            @Valid @RequestBody HotTopicAnswerRequest request
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        HotTopicAnswerResponse response = hotTopicService.addAnswerAndGetAnswerPercent(username, hotTopicId, request);
        return ApiResponse.success(response);
    }

}
