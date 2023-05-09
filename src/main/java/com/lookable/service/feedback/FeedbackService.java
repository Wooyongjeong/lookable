package com.lookable.service.feedback;

import com.lookable.domain.feedback.Feedback;
import com.lookable.dto.feedback.request.FeedbackRequest;
import com.lookable.repository.feedback.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Transactional
    public void saveFeedback(FeedbackRequest request) {
        Feedback feedback = request.toEntity();
        feedbackRepository.save(feedback);
    }

}
