package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import com.entrevistador.generadorfeedback.domain.port.FeedbackDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.annotation.Async;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedbackEntityServiceTest {
    @InjectMocks
    private FeedbackService feedbackService;
    @Mock
    private FeedbackDao feedbackDao;

    @Test
    @Async
    void crearFeedbackTest() {
        FeedbackDto feedbackDto = FeedbackDto.builder().build();
        when(this.feedbackDao.guardarPreguntar(anyString(), anyList())).thenReturn(Mono.just(feedbackDto));

        Mono<FeedbackDto> publisher = this.feedbackService.some(FeedbackDto.builder().build());

        StepVerifier
                .create(publisher)
                .expectNext(feedbackDto)
                .verifyComplete();

        for (int i = 0; i < lista; i++) {


        }

        verify(this.feedbackDao, times(1)).guardarPreguntar(anyString(), anyList());
    }
}