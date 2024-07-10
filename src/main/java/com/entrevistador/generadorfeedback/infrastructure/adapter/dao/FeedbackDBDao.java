package com.entrevistador.generadorfeedback.infrastructure.adapter.dao;

import com.entrevistador.generadorfeedback.domain.excepciones.FeedbackException;
import com.entrevistador.generadorfeedback.domain.model.Entrevista;
import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.domain.model.FeedbackResponse;
import com.entrevistador.generadorfeedback.domain.model.Pregunta;
import com.entrevistador.generadorfeedback.domain.model.PreguntaComentarioEntrevista;
import com.entrevistador.generadorfeedback.domain.model.Respuesta;
import com.entrevistador.generadorfeedback.domain.port.FeedbackDao;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.FeedbackEntity;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.FeedbackMapper;
import com.entrevistador.generadorfeedback.infrastructure.repositoy.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FeedbackDBDao implements FeedbackDao {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    @Override
    public Mono<Pregunta> guardarPreguntas(Entrevista entrevista) {
        return Mono.just(this.feedbackMapper.mapEntrevistaToFeedbackEntity(entrevista))
                .flatMap(this.feedbackRepository::save)
                .map(this.feedbackMapper::mapFeedbackEntityToPreguntaSave);
    }

    @Override
    public Mono<Respuesta> actualizarRespuestas(Respuesta respuesta) {
        return this.feedbackRepository.findByIdEntrevista(respuesta.getIdEntrevista())
                .switchIfEmpty(Mono.error(new FeedbackException("Id de estado no encontrado. ID: " + respuesta.getIdEntrevista())))
                .flatMap(feedbackEntity -> {
                    respuesta.getProcesoEntrevista().forEach(respuestaComentario -> feedbackEntity.getEntrevista()
                            .stream()
                            .filter(efb -> efb.getIdPregunta().equals(respuestaComentario.getIdPregunta()))
                            .findFirst()
                            .ifPresent(efb -> efb.setRespuesta(respuestaComentario.getRespuesta())));
                    return this.feedbackRepository.save(feedbackEntity);
                })
                .map(this.feedbackMapper::mapFeedbackEntityToRespuesta);
    }

    @Override
    public Mono<Feedback> actualizarFeedback(Feedback feedback) {
        return this.feedbackRepository.findByIdEntrevista(feedback.getIdEntrevista())
                .switchIfEmpty(Mono.error(new FeedbackException("Id de estado no encontrado. ID: " + feedback.getIdEntrevista())))
                .flatMap(feedbackEntity -> {
                    feedback.getProcesoEntrevista().forEach(feedbackComentario -> feedbackEntity.getEntrevista()
                                    .stream()
                                    .filter(efb -> efb.getIdPregunta().equals(feedbackComentario.getIdPregunta()))
                                    .findFirst()
                                    .ifPresent(efb -> efb.actualizarFeedback(feedbackComentario.getFeedback(),
                                                    feedbackComentario.getScore())));
                    return this.feedbackRepository.save(feedbackEntity);
                })
                .map(this.feedbackMapper::mapFeedbackEntityToFeedback);
    }

    @Override
    public Flux<PreguntaComentarioEntrevista> obtenerPreguntas(String entrevistaId) {
        return this.feedbackRepository.findByIdEntrevista(entrevistaId)
                .switchIfEmpty(Mono.error(new FeedbackException("Id de estado no encontrado. ID: " + entrevistaId)))
                .map(FeedbackEntity::getEntrevista)
                .flatMapMany(Flux::fromIterable)
                .map(this.feedbackMapper::mapEntrevistaFeedbackEntityToPreguntaComentario);
    }

    @Override
    public Flux<FeedbackResponse> obtenerFeedback(String entrevistaId) {
        return this.feedbackRepository.findByIdEntrevista(entrevistaId)
                .switchIfEmpty(Mono.error(new FeedbackException("Id de estado no encontrado. ID: " + entrevistaId)))
                .map(FeedbackEntity::getEntrevista)
                .flatMapMany(Flux::fromIterable)
                .map(this.feedbackMapper::mapEntrevistaFeedbackEntityToFeedbackResponse);
    }

}
