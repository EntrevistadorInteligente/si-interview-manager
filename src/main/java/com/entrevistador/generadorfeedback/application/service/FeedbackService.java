package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.application.usescases.FeedbackCreation;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import com.entrevistador.generadorfeedback.domain.port.FeedbackDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FeedbackService implements FeedbackCreation {
    private final FeedbackDao feedbackDao;

    @Override
    public Mono<FeedbackDto> some(FeedbackDto request) {
        return this.feedbackDao.createFeedback();
    }
}
