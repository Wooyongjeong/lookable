package com.lookable.controller.feedback;

import com.lookable.dto.ApiResponse;
import com.lookable.dto.feedback.request.FeedbackRequest;
import com.lookable.service.feedback.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/feedback")
@RestController
public class FeedbackApiController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ApiResponse<String> feedback(
            @Valid @RequestBody FeedbackRequest request
    ) {
        feedbackService.saveFeedback(request);
        return ApiResponse.OK;
    }

}
