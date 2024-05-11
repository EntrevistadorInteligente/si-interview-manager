package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.application.usescases.FeedbackCreation;
import com.entrevistador.generadorfeedback.domain.excepciones.FeedbackException;
import com.entrevistador.generadorfeedback.domain.jms.JmsPublisherClient;
import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import com.entrevistador.generadorfeedback.domain.port.FeedbackDao;
import com.entrevistador.generadorfeedback.domain.port.sse.SseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackService implements FeedbackCreation {

    private final FeedbackDao feedbackDao;
    private final SseService sseService;
    private final JmsPublisherClient jmsPublisherClient;

    @Override
    public Mono<FeedbackDto> some(FeedbackDto request) {
        return this.feedbackDao.createFeedback("", new ArrayList<>());
    }

    @Override
    public Mono<Void> crearEspacioEntrevista(EntrevistaDto entrevistaDto) {
        log.info("Entrevista generada");
        log.info(String.format("Preguntas de entrevista id %s generadas exitosamente",entrevistaDto.getIdEntrevista()));
        return this.feedbackDao.createFeedback(entrevistaDto.getIdEntrevista(), entrevistaDto.getPreguntas())
                .flatMap(feedbackDto ->
                        Mono.fromCallable(() -> new ObjectMapper().writeValueAsString(feedbackDto))
                                .flatMap(jsonData ->
                                        this.sseService.emitEvent(ServerSentEvent.<String>builder()
                                                .data(jsonData)
                                                .build())
                                )
                                .onErrorMap(JsonProcessingException.class, e -> {
                                    e.printStackTrace();
                                    return new FeedbackException("Error processing JSON");
                                })
                );
    }

    @Override
    public Mono<Void> iniciarSolicitudFeedback(FeedbackDto preguntasDto) {
        return this.feedbackDao.actualizarProcesoFeedback(preguntasDto)
                .flatMap(this.jmsPublisherClient::enviarsolicitudFeedback);
    }

    @Override
    public Mono<Void> guardarFeedback(FeedbackDto feedbackDto) {
        log.info(String.format("Feedback de entrevista id %s generado exitosamente",feedbackDto.getIdEntrevista()));
        return this.feedbackDao.actualizarProcesoFeedback(feedbackDto)
                .flatMap(feedback ->
                        Mono.fromCallable(() -> new ObjectMapper().writeValueAsString(feedback))
                                .flatMap(jsonData ->
                                        this.sseService.emitEvent(ServerSentEvent.<String>builder()
                                                .data(jsonData)
                                                .build())
                                )
                                .onErrorMap(JsonProcessingException.class, e -> {
                                    e.printStackTrace();
                                    return new FeedbackException("Error processing JSON");
                                })
                );
    }

}
