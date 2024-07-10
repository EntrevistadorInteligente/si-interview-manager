package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.domain.jms.JmsPublisherClient;
import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.domain.model.Notificacion;
import com.entrevistador.generadorfeedback.domain.port.FeedbackDao;
import com.entrevistador.generadorfeedback.domain.port.client.NotificacionesClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

}