package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.application.usescases.FeedbackCreation;
import com.entrevistador.generadorfeedback.application.usescases.PreguntaCreation;
import com.entrevistador.generadorfeedback.application.usescases.RespuestaCreation;
import com.entrevistador.generadorfeedback.domain.exception.FeedbackException;
import com.entrevistador.generadorfeedback.domain.port.jms.JmsPublisherClient;
import com.entrevistador.generadorfeedback.domain.model.EntrevistaFeedback;
import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.domain.model.Notificacion;
import com.entrevistador.generadorfeedback.domain.model.enums.TipoNotificacionEnum;
import com.entrevistador.generadorfeedback.domain.port.FeedbackDao;
import com.entrevistador.generadorfeedback.domain.port.client.NotificacionesClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class FeedbackService implements FeedbackCreation, PreguntaCreation, RespuestaCreation {

    private final FeedbackDao feedbackDao;
    private final JmsPublisherClient jmsPublisherClient;
    private final NotificacionesClient notificacionesClient;

    @Override
    public Mono<Void> guardarPreguntas(Feedback feedback) {
        log.info("Preguntas de entrevista id {} generadas exitosamente", feedback.getIdEntrevista());
        return this.feedbackDao.guardarPreguntas(feedback)
                .flatMap(it -> generarNotificacion(it.getUsername(), TipoNotificacionEnum.PG,
                        it.getIdEntrevista()));
    }

    @Override
    public Flux<EntrevistaFeedback> obtenerPreguntas(String entrevistaId) {
        return this.feedbackDao.obtenerPreguntas(entrevistaId);
    }

    @Override
    public Mono<Void> iniciarSolicitudFeedback(Feedback feedback) {
        return this.feedbackDao.obtenerFeedback(feedback.getIdEntrevista())
                .flatMap(Feedback::validateFeedbackProcess)
                .flatMap(unused -> this.feedbackDao.actualizarRespuestas(feedback))
                .flatMap(this.jmsPublisherClient::enviarsolicitudFeedback);
    }

    @Override
    public Mono<Void> actualizarFeedback(Feedback feedback) {
        return this.feedbackDao.actualizarFeedback(feedback)
                .flatMap(it -> generarNotificacion(it.getUsername(), TipoNotificacionEnum.FG,
                        it.getIdEntrevista()));
    }

    @Override
    public Flux<EntrevistaFeedback> obtenerFeedback(String entrevistaId) {
        return this.feedbackDao.obtenerEntrevistaFeedback(entrevistaId);
    }

    private Mono<Void> generarNotificacion(String userId,
                                           TipoNotificacionEnum tipoNotificacionEnum,
                                           Object object) {
        return Mono.fromCallable(() -> new ObjectMapper().writeValueAsString(object))
                .flatMap(jsonData -> {
                    Notificacion notificacion = Notificacion.builder()
                            .tipo(tipoNotificacionEnum)
                            .mensaje(jsonData)
                            .build();
                    return this.notificacionesClient.enviar(userId, notificacion)
                            .onErrorMap(JsonProcessingException.class, e -> {
                                log.error("Error processing JSON", e);
                                return new FeedbackException("Error processing JSON");
                            });
                });
    }

}
