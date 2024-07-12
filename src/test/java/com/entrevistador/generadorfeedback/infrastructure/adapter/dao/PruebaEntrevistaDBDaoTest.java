package com.entrevistador.generadorfeedback.infrastructure.adapter.dao;

import com.entrevistador.generadorfeedback.domain.model.FeedbackResponse;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.EntrevistaFeedbackEntity;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.FeedbackMapper;
import com.entrevistador.generadorfeedback.infrastructure.repositoy.FeedbackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PruebaEntrevistaDBDaoTest {
    @InjectMocks
    private PruebaEntrevistaDBDao pruebaEntrevistaDBDao;
    @Mock
    private FeedbackRepository feedbackRepository;
    @Mock
    private FeedbackMapper feedbackMapper;

    @Test
    void testGetPreguntas() {
        FeedbackResponse feedbackResponse = FeedbackResponse.builder().build();

        when(this.feedbackRepository.obtenerPreguntas(anyString(), anyInt()))
                .thenReturn(Flux.just(EntrevistaFeedbackEntity.builder().build()));
        when(this.feedbackMapper.mapEntrevistaFeedbackEntityToFeedbackResponseDto(any())).thenReturn(feedbackResponse);

        Flux<FeedbackResponse> publisher = this.pruebaEntrevistaDBDao.getPreguntas("any", 1);

        StepVerifier
                .create(publisher)
                .expectNext(feedbackResponse)
                .verifyComplete();

        verify(this.feedbackRepository, times(1)).obtenerPreguntas(anyString(), anyInt());
        verify(this.feedbackMapper, times(1)).mapEntrevistaFeedbackEntityToFeedbackResponseDto(any());
    }
}