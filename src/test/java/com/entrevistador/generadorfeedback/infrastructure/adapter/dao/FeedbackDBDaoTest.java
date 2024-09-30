package com.entrevistador.generadorfeedback.infrastructure.adapter.dao;

import com.entrevistador.generadorfeedback.domain.model.EntrevistaFeedback;
import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.FeedbackEntity;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.out.FeedbackDBMapper;
import com.entrevistador.generadorfeedback.infrastructure.repositoy.FeedbackRepository;
import com.entrevistador.generadorfeedback.utils.FeedbackMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedbackDBDaoTest {
    @InjectMocks
    private FeedbackDBDao feedbackDBDao;
    @Mock
    private FeedbackRepository feedbackRepository;
    @Mock
    private FeedbackDBMapper feedbackDBMapper;

    @Test
    void testGuardarPreguntas() {
        Feedback feedback = Feedback.builder().build();

        when(this.feedbackDBMapper.mapInFeedbackToFeedbackEntity(any())).thenReturn(FeedbackEntity.builder().build());
        when(this.feedbackRepository.save(any())).thenReturn(Mono.just(FeedbackEntity.builder().build()));
        when(this.feedbackDBMapper.mapOutFeedbackEntityToFeedback(any())).thenReturn(feedback);

        Mono<Feedback> publisher = this.feedbackDBDao.guardarPreguntas(Feedback.builder().build());

        StepVerifier
                .create(publisher)
                .expectNext(feedback)
                .verifyComplete();

        verify(this.feedbackDBMapper, times(1)).mapInFeedbackToFeedbackEntity(any());
        verify(this.feedbackRepository, times(1)).save(any());
        verify(this.feedbackDBMapper, times(1)).mapOutFeedbackEntityToFeedback(any());
    }

    @Test
    void testActualizarRespuestas() throws IOException {
        FeedbackEntity feedbackEntity = FeedbackMock.getInstance().getFeedbackEntity();
        Feedback feedback = FeedbackMock.getInstance().getFeedback();

        when(this.feedbackRepository.findByIdEntrevista(anyString())).thenReturn(Mono.just(feedbackEntity));
        when(this.feedbackRepository.save(any())).thenReturn(Mono.just(FeedbackEntity.builder().build()));
        when(this.feedbackDBMapper.mapOutFeedbackEntityToFeedback(any())).thenReturn(feedback);

        Mono<Feedback> publisher = this.feedbackDBDao.actualizarRespuestas(feedback);

        StepVerifier
                .create(publisher)
                .expectNext(feedback)
                .verifyComplete();

        verify(this.feedbackRepository, times(1)).findByIdEntrevista(any());
        verify(this.feedbackRepository, times(1)).save(any());
        verify(this.feedbackDBMapper, times(1)).mapOutFeedbackEntityToFeedback(any());
    }

    @Test
    void testActualizarFeedback() throws IOException {
        FeedbackEntity feedbackEntity = FeedbackMock.getInstance().getFeedbackEntity();
        FeedbackEntity feedbackEntityUpdated = FeedbackMock.getInstance().getFeedbackEntityUpdated();
        Feedback feedback = FeedbackMock.getInstance().getFeedback();

        when(this.feedbackRepository.findByIdEntrevista(anyString())).thenReturn(Mono.just(feedbackEntity));
        when(this.feedbackRepository.save(any())).thenReturn(Mono.just(feedbackEntityUpdated));
        when(this.feedbackDBMapper.mapOutFeedbackEntityToFeedback(any())).thenReturn(feedback);

        Mono<Feedback> publisher = this.feedbackDBDao.actualizarFeedback(feedback);

        StepVerifier
                .create(publisher)
                .assertNext(actualFeedback -> {
                    assertEquals(feedbackEntityUpdated.getEntrevista().get(0).getFeedback(), actualFeedback.getProcesoEntrevista().get(0).getFeedback());
                    assertEquals(feedbackEntityUpdated.getEntrevista().get(0).getScore(), actualFeedback.getProcesoEntrevista().get(0).getScore());
                })
                .verifyComplete();

        verify(this.feedbackRepository, times(1)).findByIdEntrevista(any());
        verify(this.feedbackRepository, times(1)).save(any());
        verify(this.feedbackDBMapper, times(1)).mapOutFeedbackEntityToFeedback(any());
    }

    @Test
    void testObtenerPreguntas() throws IOException {
        FeedbackEntity feedbackEntity = FeedbackMock.getInstance().getFeedbackEntity();
        EntrevistaFeedback entrevistaFeedback = EntrevistaFeedback.builder().build();

        when(this.feedbackRepository.findByIdEntrevista(anyString())).thenReturn(Mono.just(feedbackEntity));
        when(this.feedbackDBMapper.mapOutEntrevistaFeedbackEntityToEntrevistaFeedback(any())).thenReturn(entrevistaFeedback);

        Flux<EntrevistaFeedback> publisher = this.feedbackDBDao.obtenerPreguntas("any");

        StepVerifier
                .create(publisher)
                .expectNext(entrevistaFeedback)
                .verifyComplete();

        verify(this.feedbackRepository, times(1)).findByIdEntrevista(any());
        verify(this.feedbackDBMapper, times(1)).mapOutEntrevistaFeedbackEntityToEntrevistaFeedback(any());
    }

    @Test
    void testObtenerEntrevistaFeedback() throws IOException {
        FeedbackEntity feedbackEntity = FeedbackMock.getInstance().getFeedbackEntity();
        EntrevistaFeedback feedbackResponse = EntrevistaFeedback.builder().build();

        when(this.feedbackRepository.findByIdEntrevista(anyString())).thenReturn(Mono.just(feedbackEntity));
        when(this.feedbackDBMapper.mapOutEntrevistaFeedbackEntityToEntrevistaFeedback(any())).thenReturn(feedbackResponse);

        Flux<EntrevistaFeedback> publisher = this.feedbackDBDao.obtenerEntrevistaFeedback("any");

        StepVerifier.
                create(publisher)
                .expectNext(feedbackResponse)
                .verifyComplete();

        verify(this.feedbackRepository, times(1)).findByIdEntrevista(any());
        verify(this.feedbackDBMapper, times(1)).mapOutEntrevistaFeedbackEntityToEntrevistaFeedback(any());
    }
}