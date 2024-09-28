package com.entrevistador.generadorfeedback.infrastructure.adapter.dao;

import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevista;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.EntrevistaEntity;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.FeedbackMapper;
import com.entrevistador.generadorfeedback.infrastructure.repositoy.EntrevistaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PruebaEntrevistaDBDaoTest {
    @InjectMocks
    private PruebaEntrevistaDBDao pruebaEntrevistaDBDao;
    @Mock
    private EntrevistaRepository entrevistaRepository;
    @Mock
    private FeedbackMapper feedbackMapper;

    @Test
    void testGetPreguntas() {
        PruebaEntrevista feedbackResponse = PruebaEntrevista.builder().build();

        when(this.entrevistaRepository.findAllByRol(anyString()))
                .thenReturn(Flux.just(EntrevistaEntity.builder().build()));
        when(this.feedbackMapper.mapEntrevistaEntityToPruebaEntrevistaResponse(any())).thenReturn(feedbackResponse);

        Flux<PruebaEntrevista> publisher = this.pruebaEntrevistaDBDao.getPreguntas("any");

        StepVerifier
                .create(publisher)
                .expectNext(feedbackResponse)
                .verifyComplete();

        verify(this.entrevistaRepository, times(1)).findAllByRol(anyString());
        verify(this.feedbackMapper, times(1)).mapEntrevistaEntityToPruebaEntrevistaResponse(any());
    }
}