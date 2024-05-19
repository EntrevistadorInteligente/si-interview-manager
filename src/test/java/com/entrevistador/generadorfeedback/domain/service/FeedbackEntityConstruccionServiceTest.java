package com.entrevistador.generadorfeedback.domain.service;

import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class FeedbackEntityConstruccionServiceTest {
    @InjectMocks
    private FeedbackConstruccionService feedbackConstruccionService;

    @Test
    void testCreateFeedback() {
        Mono<Void> publisher = this.feedbackConstruccionService.createFeedback(FeedbackDto.builder().build());

        StepVerifier
                .create(publisher)
                .verifyComplete();
    }
}