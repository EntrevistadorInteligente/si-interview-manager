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
import java.util.List;

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
        return this.feedbackDao.createFeedback(entrevistaDto.getIdEntrevista(), entrevistaDto.getPreguntas())
                .flatMap(feedbackDto -> {
                    List<String> preguntas = entrevistaDto.getPreguntas();
                    List<String> respuestas = feedbackDto.getRespuestas();
                    List<String> respuestasFiltradas = new ArrayList<>();
                    for (int i = 0; i < preguntas.size(); i++) {
                        String pregunta = preguntas.get(i);
                        String respuesta = respuestas.get(i);
                        respuestasFiltradas.add(pregunta + ": " + respuesta);
                    }
                    return Mono.fromCallable(() -> new ObjectMapper().writeValueAsString(feedbackDto))
                            .flatMap(jsonData ->
                                    this.sseService.emitEvent(ServerSentEvent.<String>builder()
                                            .data(jsonData)
                                            .build())
                            )
                            .onErrorMap(JsonProcessingException.class, e -> {
                                e.printStackTrace();
                                return new FeedbackException("Error processing JSON");
                            });
                });
    }

    @Override
    public Mono<Void> iniciarSolicitudFeedback(FeedbackDto preguntasDto) {
        return this.feedbackDao.actualizarProcesoFeedback(preguntasDto)
                .flatMap(this.jmsPublisherClient::enviarsolicitudFeedback);
    }

    @Override
    public Mono<Void> guardarFeedback(FeedbackDto feedbackDto) {
        log.info("Feedback generado");
        List<String> respuestas = feedbackDto.getRespuestas();
        List<String> respuestasFiltradas = new ArrayList<>();
        for (int i = 0; i < respuestas.size(); i++) {
            String pregunta = feedbackDto.getPreguntas().get(i);
            String respuesta = respuestas.get(i);
            respuestasFiltradas.add(pregunta + ": " + respuesta);
        }
        return Mono.fromCallable(() -> new ObjectMapper().writeValueAsString(feedbackDto))
                .flatMap(jsonData ->
                        this.sseService.emitEvent(ServerSentEvent.<String>builder()
                                .data(jsonData)
                                .build())
                )
                .onErrorMap(JsonProcessingException.class, e -> {
                    e.printStackTrace();
                    return new FeedbackException("Error processing JSON");
                });
    }
}