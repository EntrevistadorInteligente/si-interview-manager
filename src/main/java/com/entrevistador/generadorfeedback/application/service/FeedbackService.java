package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.application.usescases.FeedbackCreation;
import com.entrevistador.generadorfeedback.application.usescases.PreguntaCreation;
import com.entrevistador.generadorfeedback.application.usescases.RespuestaCreation;
import com.entrevistador.generadorfeedback.domain.exception.FeedbackException;
import com.entrevistador.generadorfeedback.domain.jms.JmsPublisherClient;
import com.entrevistador.generadorfeedback.domain.model.Entrevista;
import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.domain.model.FeedbackResponse;
import com.entrevistador.generadorfeedback.domain.model.Notificacion;
import com.entrevistador.generadorfeedback.domain.model.PreguntaComentarioEntrevista;
import com.entrevistador.generadorfeedback.domain.model.Respuesta;
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
    public Mono<Void> guardarPreguntas(Entrevista entrevista) {
        log.info("Preguntas de entrevista id {} generadas exitosamente", entrevista.getIdEntrevista());
        return this.feedbackDao.guardarPreguntas(entrevista)
                .flatMap(pregunta -> generarNotificacion(pregunta.getUsername(), TipoNotificacionEnum.PG,
                        pregunta.getIdEntrevista()));
    }

    @Override
    public Flux<PreguntaComentarioEntrevista> obtenerPreguntas(String entrevistaId) {
        return this.feedbackDao.obtenerPreguntas(entrevistaId);
    }

    @Override
    public Mono<Void> iniciarSolicitudFeedback(Respuesta respuesta) {
        return this.feedbackDao.obtenerFeedback(respuesta.getIdEntrevista())
                .flatMap(Feedback::validateFeedbackProcess)
                .flatMap(unused -> this.feedbackDao.actualizarRespuestas(respuesta))
                .flatMap(this.jmsPublisherClient::enviarsolicitudFeedback);
    }

    @Override
    public Mono<Void> actualizarFeedback(Feedback feedback) {
        return this.feedbackDao.actualizarFeedback(feedback)
                .flatMap(fb -> generarNotificacion(fb.getUsername(), TipoNotificacionEnum.FG,
                        fb.getIdEntrevista()));
    }

    @Override
    public Flux<FeedbackResponse> obtenerFeedback(String entrevistaId) {
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
