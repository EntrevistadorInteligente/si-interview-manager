package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.application.usescases.FeedbackCreation;
import com.entrevistador.generadorfeedback.application.usescases.PreguntaCreation;
import com.entrevistador.generadorfeedback.application.usescases.RespuestaCreation;
import com.entrevistador.generadorfeedback.domain.jms.JmsPublisherClient;
import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackComentarioDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackResponseDto;
import com.entrevistador.generadorfeedback.domain.model.dto.PreguntaComentarioDto;
import com.entrevistador.generadorfeedback.domain.model.dto.RespuestaDto;
import com.entrevistador.generadorfeedback.domain.model.enums.TipoNotificacionEnum;
import com.entrevistador.generadorfeedback.domain.port.FeedbackDao;
import com.entrevistador.generadorfeedback.domain.port.client.NotificacionesClient;
import com.entrevistador.generadorfeedback.domain.port.sse.SseService;
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
    private final SseService sseService;
    private final JmsPublisherClient jmsPublisherClient;
    private final NotificacionesClient notificacionesClient;

    @Override
    public Mono<Void> guardarPreguntas(EntrevistaDto entrevistaDto) {
        log.info(String.format("Preguntas de entrevista id %s generadas exitosamente", entrevistaDto.getIdEntrevista()));
        return this.feedbackDao.guardarPreguntas(entrevistaDto)
                .flatMap(entrevista ->
                        this.notificacionesClient.generarNotificacion(
                                entrevista.getUsername(),
                                TipoNotificacionEnum.PG,
                                entrevista.getIdEntrevista())
                );
    }

    @Override
    public Flux<PreguntaComentarioDto> obtenerPreguntas(String entrevistaId) {
        return this.feedbackDao.obtenerPreguntas(entrevistaId);
    }

    @Override
    public Mono<Void> iniciarSolicitudFeedback(RespuestaDto respuestaDto) {
        return this.feedbackDao.actualizarRespuestas(respuestaDto)
                .flatMap(this.jmsPublisherClient::enviarsolicitudFeedback);
    }

    @Override
    public Mono<Void> actualizarFeedback(FeedbackDto feedbackDto) {
        return this.feedbackDao.actualizarFeedback(feedbackDto)
                .flatMap(feedback ->
                        this.notificacionesClient.generarNotificacion(feedback.getUsername(),
                                TipoNotificacionEnum.FG,
                                feedback.getIdEntrevista())
                );
    }

    @Override
    public Flux<FeedbackResponseDto> obtenerFeedback(String entrevistaId) {
        return this.feedbackDao.obtenerFeedback(entrevistaId);
    }

}
