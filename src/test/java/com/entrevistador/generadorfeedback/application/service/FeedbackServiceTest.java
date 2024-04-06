package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import com.entrevistador.generadorfeedback.domain.port.FeedbackDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {
    @InjectMocks
    private FeedbackService feedbackService;
    @Mock
    private FeedbackDao feedbackDao;

    @Test
    void testSome() {
        FeedbackDto feedbackDto = FeedbackDto.builder().build();
        when(this.feedbackDao.createFeedback()).thenReturn(Mono.just(feedbackDto));

        Mono<FeedbackDto> publisher = this.feedbackService.some(FeedbackDto.builder().build());

        StepVerifier
                .create(publisher)
                .expectNext(feedbackDto)
                .verifyComplete();

        verify(this.feedbackDao, times(1)).createFeedback();
    }
}