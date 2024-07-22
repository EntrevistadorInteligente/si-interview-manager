package com.entrevistador.generadorfeedback.infrastructure.adapter.dao;

import com.entrevistador.generadorfeedback.domain.model.Entrevista;
import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.domain.model.FeedbackResponse;
import com.entrevistador.generadorfeedback.domain.model.Pregunta;
import com.entrevistador.generadorfeedback.domain.model.PreguntaComentarioEntrevista;
import com.entrevistador.generadorfeedback.domain.model.Respuesta;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.FeedbackEntity;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.FeedbackMapper;
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
    private FeedbackMapper feedbackMapper;

    @Test
    void testguardarPreguntas() {
        Pregunta pregunta = Pregunta.builder().build();

        when(this.feedbackMapper.mapEntrevistaToFeedbackEntity(any())).thenReturn(FeedbackEntity.builder().build());
        when(this.feedbackRepository.save(any())).thenReturn(Mono.just(FeedbackEntity.builder().build()));
        when(this.feedbackMapper.mapFeedbackEntityToPreguntaSave(any())).thenReturn(pregunta);

        Mono<Pregunta> publisher = this.feedbackDBDao.guardarPreguntas(Entrevista.builder().build());

        StepVerifier
                .create(publisher)
                .expectNext(pregunta)
                .verifyComplete();

        verify(this.feedbackMapper, times(1)).mapEntrevistaToFeedbackEntity(any());
        verify(this.feedbackRepository, times(1)).save(any());
        verify(this.feedbackMapper, times(1)).mapFeedbackEntityToPreguntaSave(any());
    }

    @Test
    void testActualizarRespuestas() throws IOException {
        FeedbackEntity feedbackEntity = FeedbackMock.getInstance().getFeedbackEntity();
        Respuesta respuesta = FeedbackMock.getInstance().getRespuesta();

        when(this.feedbackRepository.findByIdEntrevista(anyString())).thenReturn(Mono.just(feedbackEntity));
        when(this.feedbackRepository.save(any())).thenReturn(Mono.just(FeedbackEntity.builder().build()));
        when(this.feedbackMapper.mapFeedbackEntityToRespuesta(any())).thenReturn(respuesta);

        Mono<Respuesta> publisher = this.feedbackDBDao.actualizarRespuestas(respuesta);

        StepVerifier
                .create(publisher)
                .expectNext(respuesta)
                .verifyComplete();

        verify(this.feedbackRepository, times(1)).findByIdEntrevista(any());
        verify(this.feedbackRepository, times(1)).save(any());
        verify(this.feedbackMapper, times(1)).mapFeedbackEntityToRespuesta(any());
    }

    @Test
    void testActualizarFeedback() throws IOException {
        FeedbackEntity feedbackEntity = FeedbackMock.getInstance().getFeedbackEntity();
        FeedbackEntity feedbackEntityUpdated = FeedbackMock.getInstance().getFeedbackEntityUpdated();
        Feedback feedback = FeedbackMock.getInstance().getFeedback();

        when(this.feedbackRepository.findByIdEntrevista(anyString())).thenReturn(Mono.just(feedbackEntity));
        when(this.feedbackRepository.save(any())).thenReturn(Mono.just(feedbackEntityUpdated));
        when(this.feedbackMapper.mapFeedbackEntityToFeedback(any())).thenReturn(feedback);

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
        verify(this.feedbackMapper, times(1)).mapFeedbackEntityToFeedback(any());
    }

    @Test
    void testObtenerPreguntas() throws IOException {
        FeedbackEntity feedbackEntity = FeedbackMock.getInstance().getFeedbackEntity();
        PreguntaComentarioEntrevista preguntaComentarioEntrevista = PreguntaComentarioEntrevista.builder().build();

        when(this.feedbackRepository.findByIdEntrevista(anyString())).thenReturn(Mono.just(feedbackEntity));
        when(this.feedbackMapper.mapEntrevistaFeedbackEntityToPreguntaComentario(any())).thenReturn(preguntaComentarioEntrevista);

        Flux<PreguntaComentarioEntrevista> publisher = this.feedbackDBDao.obtenerPreguntas("any");

        StepVerifier
                .create(publisher)
                .expectNext(preguntaComentarioEntrevista)
                .verifyComplete();

        verify(this.feedbackRepository, times(1)).findByIdEntrevista(any());
        verify(this.feedbackMapper, times(1)).mapEntrevistaFeedbackEntityToPreguntaComentario(any());
    }

    @Test
    void testObtenerEntrevistaFeedback() throws IOException {
        FeedbackEntity feedbackEntity = FeedbackMock.getInstance().getFeedbackEntity();
        FeedbackResponse feedbackResponse = FeedbackResponse.builder().build();

        when(this.feedbackRepository.findByIdEntrevista(anyString())).thenReturn(Mono.just(feedbackEntity));
        when(this.feedbackMapper.mapEntrevistaFeedbackEntityToFeedbackResponse(any())).thenReturn(feedbackResponse);

        Flux<FeedbackResponse> publisher = this.feedbackDBDao.obtenerEntrevistaFeedback("any");

        StepVerifier.
                create(publisher)
                .expectNext(feedbackResponse)
                .verifyComplete();

        verify(this.feedbackRepository, times(1)).findByIdEntrevista(any());
        verify(this.feedbackMapper, times(1)).mapEntrevistaFeedbackEntityToFeedbackResponse(any());
    }
}