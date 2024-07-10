package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.application.usescases.FeedbackCreation;
import com.entrevistador.generadorfeedback.application.usescases.PreguntaCreation;
import com.entrevistador.generadorfeedback.application.usescases.RespuestaCreation;
import com.entrevistador.generadorfeedback.domain.excepciones.FeedbackException;
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
    public Mono<Void> guardarPreguntas(Entrevista entrevistaDto) {
        log.info("Preguntas de entrevista id {} generadas exitosamente", entrevistaDto.getIdEntrevista());
        return this.feedbackDao.guardarPreguntas(entrevistaDto)
                .flatMap(pregunta -> generarNotificacion(pregunta.getUsername(), TipoNotificacionEnum.PG,
                        pregunta.getIdEntrevista()));
    }

    @Override
    public Flux<PreguntaComentarioEntrevista> obtenerPreguntas(String entrevistaId) {
        return this.feedbackDao.obtenerPreguntas(entrevistaId);
    }

    @Override
    public Mono<Void> iniciarSolicitudFeedback(Respuesta respuestaDto) {
        return this.feedbackDao.actualizarRespuestas(respuestaDto)
                .flatMap(this.jmsPublisherClient::enviarsolicitudFeedback);
    }

    @Override
    public Mono<Void> actualizarFeedback(Feedback feedbackDto) {
        return this.feedbackDao.actualizarFeedback(feedbackDto)
                .flatMap(feedback ->
                        generarNotificacion(feedback.getUsername(),
                                TipoNotificacionEnum.FG,
                                feedback.getIdEntrevista())
                );
    }

    @Override
    public Flux<FeedbackResponse> obtenerFeedback(String entrevistaId) {
        return this.feedbackDao.obtenerFeedback(entrevistaId);
    }

    private Mono<Void> generarNotificacion(String userId,
                                           TipoNotificacionEnum notificacion,
                                           Object object) {
        return
                Mono.fromCallable(() -> new ObjectMapper().writeValueAsString(object))
                        .flatMap(jsonData ->
                                this.notificacionesClient.enviar(userId, Notificacion.builder()
                                                .tipo(notificacion)
                                                .mensaje(jsonData)
                                                .build()
                                        )
                                        .onErrorMap(JsonProcessingException.class, e -> {
                                            log.error("Error processing JSON", e);
                                            return new FeedbackException("Error processing JSON");
                                        }));
    }

}
