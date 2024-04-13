package com.entrevistador.generadorfeedback.domain.service;

import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import reactor.core.publisher.Mono;

public class FeedbackConstruccionService {

    public Mono<Void> createFeedback(FeedbackDto feedbackDto) {
        return Mono.empty();
    }

}
