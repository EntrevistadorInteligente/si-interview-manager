package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.domain.exception.FeedbackProcessStatusException;
import com.entrevistador.generadorfeedback.domain.jms.JmsPublisherClient;
import com.entrevistador.generadorfeedback.domain.model.Entrevista;
import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.domain.model.FeedbackResponse;
import com.entrevistador.generadorfeedback.domain.model.Notificacion;
import com.entrevistador.generadorfeedback.domain.model.Pregunta;
import com.entrevistador.generadorfeedback.domain.model.PreguntaComentarioEntrevista;
import com.entrevistador.generadorfeedback.domain.model.Respuesta;
import com.entrevistador.generadorfeedback.domain.model.enums.TipoNotificacionEnum;
import com.entrevistador.generadorfeedback.domain.port.FeedbackDao;
import com.entrevistador.generadorfeedback.domain.port.client.NotificacionesClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {
    @InjectMocks
    private FeedbackService feedbackService;
    @Mock
    private FeedbackDao feedbackDao;
    @Mock
    private JmsPublisherClient jmsPublisherClient;
    @Mock
    private NotificacionesClient notificacionesClient;

    @Test
    void testGuardarPreguntas() {
        Pregunta pregunta = Pregunta.builder().username("any").idEntrevista("any").build();
        when(this.feedbackDao.guardarPreguntas(any())).thenReturn(Mono.just(pregunta));
        when(this.notificacionesClient.enviar(anyString(), any())).thenReturn(Mono.empty());

        Mono<Void> publisher = this.feedbackService.guardarPreguntas(Entrevista.builder().build());

        StepVerifier
                .create(publisher)
                .verifyComplete();

        verify(this.feedbackDao, times(1)).guardarPreguntas(any());
        verify(this.notificacionesClient, times(1)).enviar(anyString(), any());
    }

    @Test
    void testObtenerPreguntas() {
        PreguntaComentarioEntrevista preguntaComentarioEntrevista = PreguntaComentarioEntrevista.builder().build();
        when(this.feedbackDao.obtenerPreguntas(anyString())).thenReturn(Flux.just(preguntaComentarioEntrevista));

        Flux<PreguntaComentarioEntrevista> publisher = this.feedbackService.obtenerPreguntas("any");

        StepVerifier
                .create(publisher)
                .expectNext(preguntaComentarioEntrevista)
                .verifyComplete();

        verify(this.feedbackDao, times(1)).obtenerPreguntas(anyString());
    }

    @Test
    void testIniciarSolicitudFeedback() {
        Feedback feedback = Feedback.builder().feedbackProcess(TipoNotificacionEnum.GF).build();

        when(this.feedbackDao.obtenerFeedback(anyString())).thenReturn(Mono.just(feedback));
        when(this.feedbackDao.actualizarRespuestas(any())).thenReturn(Mono.just(Respuesta.builder().build()));
        when(this.jmsPublisherClient.enviarsolicitudFeedback(any())).thenReturn(Mono.empty());

        Mono<Void> publisher = this.feedbackService.iniciarSolicitudFeedback(Respuesta.builder().idEntrevista("any").build());

        StepVerifier
                .create(publisher)
                .verifyComplete();

        verify(this.feedbackDao, times(1)).obtenerFeedback(anyString());
        verify(this.feedbackDao, times(1)).actualizarRespuestas(any());
        verify(this.jmsPublisherClient, times(1)).enviarsolicitudFeedback(any());
    }

    @Test
    void testIniciarSolicitudFeedbackException() {
        Feedback feedback = Feedback.builder().feedbackProcess(TipoNotificacionEnum.FG).build();

        when(this.feedbackDao.obtenerFeedback(anyString())).thenReturn(Mono.just(feedback));

        Mono<Void> publisher = this.feedbackService.iniciarSolicitudFeedback(Respuesta.builder().idEntrevista("any").build());

        StepVerifier
                .create(publisher)
                .expectError(FeedbackProcessStatusException.class)
                .verify();

        verify(this.feedbackDao, times(1)).obtenerFeedback(anyString());
        verify(this.feedbackDao, times(0)).actualizarRespuestas(any());
        verify(this.jmsPublisherClient, times(0)).enviarsolicitudFeedback(any());
    }

    @Test
    void shouldUpdateFeedbackWhenValidRequest() {
        Feedback feedbackDto = Feedback.builder()
                .idEntrevista("idEntrevista")
                .username("username")
                .build();
        when(this.feedbackDao.actualizarFeedback(any(Feedback.class))).thenReturn(Mono.just(feedbackDto));
        when(this.notificacionesClient.enviar(anyString(), any(Notificacion.class)))
                .thenReturn(Mono.empty());

        Mono<Void> publisher = this.feedbackService.actualizarFeedback(Feedback.builder().build());

        StepVerifier
                .create(publisher)
                .verifyComplete();

        verify(this.feedbackDao, times(1)).actualizarFeedback(any(Feedback.class));
    }

    @Test
    void testObtenerFeedback() {
        FeedbackResponse feedbackResponse = FeedbackResponse.builder().build();
        when(this.feedbackDao.obtenerEntrevistaFeedback(anyString())).thenReturn(Flux.just(feedbackResponse));

        Flux<FeedbackResponse> publisher = this.feedbackService.obtenerFeedback("any");

        StepVerifier
                .create(publisher)
                .expectNext(feedbackResponse)
                .verifyComplete();

        verify(this.feedbackDao, times(1)).obtenerEntrevistaFeedback(anyString());
    }
}