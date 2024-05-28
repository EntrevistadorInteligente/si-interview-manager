package com.entrevistador.generadorfeedback.infrastructure.adapter.dao;

import com.entrevistador.generadorfeedback.domain.excepciones.FeedbackException;
import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackComentarioDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackResponseDto;
import com.entrevistador.generadorfeedback.domain.model.dto.PreguntaComentarioDto;
import com.entrevistador.generadorfeedback.domain.model.dto.PreguntaDto;
import com.entrevistador.generadorfeedback.domain.model.dto.RespuestaComentarioDto;
import com.entrevistador.generadorfeedback.domain.model.dto.RespuestaDto;
import com.entrevistador.generadorfeedback.domain.port.FeedbackDao;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.EntrevistaFeedbackEntity;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.FeedbackEntity;
import com.entrevistador.generadorfeedback.infrastructure.repositoy.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FeedbackDBDao implements FeedbackDao {
    private final FeedbackRepository feedbackRepository;

    @Override
    public Mono<PreguntaDto> guardarPreguntas(EntrevistaDto entrevistaDto) {
        return this.feedbackRepository.save(FeedbackEntity.builder()
                        .idEntrevista(entrevistaDto.getIdEntrevista())
                        .username(entrevistaDto.getUsername())
                        .entrevista(entrevistaDto.getPreguntas().stream().map(s -> EntrevistaFeedbackEntity.builder()
                                .idPregunta(UUID.randomUUID().toString())
                                .pregunta(s.getPregunta())
                                .build()).toList())
                        .build())
                .map(feedback -> PreguntaDto.builder()
                        .idEntrevista(feedback.getIdEntrevista())
                        .username(feedback.getUsername())
                        .procesoEntrevista(feedback.getEntrevista().stream()
                                .map(fb -> PreguntaComentarioDto.builder()
                                        .idPregunta(fb.getIdPregunta())
                                        .pregunta(fb.getRespuesta())
                                        .build())
                                .toList())
                        .build());
    }

    @Override
    public Mono<RespuestaDto> actualizarRespuestas(RespuestaDto respuestaDto) {
        return this.feedbackRepository.findByIdEntrevista(respuestaDto.getIdEntrevista())
                .switchIfEmpty(Mono.error(new FeedbackException("Id de estado no encontrado. ID: " + respuestaDto.getIdEntrevista())))
                .flatMap(feedbackEntity -> {
                    respuestaDto.getProcesoEntrevista().forEach(respuestaComentarioDto -> {
                        feedbackEntity.getEntrevista().stream()
                                .filter(fb -> fb.getIdPregunta().equals(respuestaComentarioDto.getIdPregunta()))
                                .findFirst()
                                .ifPresent(fb -> fb.setRespuesta(respuestaComentarioDto.getRespuesta()));
                    });
                    return this.feedbackRepository.save(feedbackEntity);
                })
                .map(feedbackEntity -> RespuestaDto.builder()
                        .idEntrevista(feedbackEntity.getIdEntrevista())
                        .username(feedbackEntity.getUsername())
                        .procesoEntrevista(feedbackEntity.getEntrevista().stream()
                                .map(fb -> RespuestaComentarioDto.builder()
                                        .idPregunta(fb.getIdPregunta())
                                        .respuesta(fb.getRespuesta())
                                        .build())
                                .toList())
                        .build());
    }

    @Override
    public Mono<FeedbackDto> actualizarFeedback(FeedbackDto feedbackDto) {
        return this.feedbackRepository.findByIdEntrevista(feedbackDto.getIdEntrevista())
                .switchIfEmpty(Mono.error(new FeedbackException("Id de estado no encontrado. ID: " + feedbackDto.getIdEntrevista())))
                .flatMap(feedbackEntity -> {
                    feedbackDto.getProcesoEntrevista().forEach(feedbackComentarioDto -> {
                        feedbackEntity.getEntrevista().stream()
                                .filter(fb -> fb.getIdPregunta().equals(feedbackComentarioDto.getIdPregunta()))
                                .findFirst()
                                .ifPresent(fb ->
                                        fb.actualzarFeedback(feedbackComentarioDto.getFeedback(),
                                                feedbackComentarioDto.getScore())
                                );
                    });
                    return this.feedbackRepository.save(feedbackEntity);
                })
                .map(feedbackEntity -> FeedbackDto.builder()
                        .idEntrevista(feedbackEntity.getIdEntrevista())
                        .username(feedbackEntity.getUsername())
                        .procesoEntrevista(feedbackEntity.getEntrevista().stream()
                                .map(fb -> FeedbackComentarioDto.builder()
                                        .idPregunta(fb.getIdPregunta())
                                        .feedback(fb.getFeedback())
                                        .build())
                                .toList())
                        .build());
    }

    @Override
    public Flux<PreguntaComentarioDto> obtenerPreguntas(String entrevistaId) {
        return this.feedbackRepository.findByIdEntrevista(entrevistaId)
                .switchIfEmpty(Mono.error(new FeedbackException("Id de estado no encontrado. ID: " + entrevistaId)))
                .flatMapMany(feedbackEntity -> {
                    List<PreguntaComentarioDto> preguntas = feedbackEntity.getEntrevista().stream()
                            .map(fb -> PreguntaComentarioDto.builder()
                                    .idPregunta(fb.getIdPregunta())
                                    .pregunta(fb.getPregunta())
                                    .build())
                            .toList();
                    return Flux.fromIterable(preguntas);
                });

    }

    @Override
    public Flux<FeedbackResponseDto> obtenerFeedback(String entrevistaId) {
        return this.feedbackRepository.findByIdEntrevista(entrevistaId)
                .switchIfEmpty(Mono.error(new FeedbackException("Id de estado no encontrado. ID: " + entrevistaId)))
                .flatMapMany(feedbackEntity -> {
                    List<FeedbackResponseDto> feedback = feedbackEntity.getEntrevista().stream()
                            .map(fb -> FeedbackResponseDto.builder()
                                    .idPregunta(fb.getIdPregunta())
                                    .pregunta(fb.getPregunta())
                                    .respuesta(fb.getRespuesta())
                                    .feedback(fb.getFeedback())
                                    .build())
                            .toList();
                    return Flux.fromIterable(feedback);
                });
    }

}
