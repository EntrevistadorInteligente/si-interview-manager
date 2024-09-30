package com.entrevistador.generadorfeedback.infrastructure.adapter.dao;

import com.entrevistador.generadorfeedback.domain.exception.FeedbackException;
import com.entrevistador.generadorfeedback.domain.model.EntrevistaFeedback;
import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.domain.model.enums.TipoNotificacionEnum;
import com.entrevistador.generadorfeedback.domain.port.FeedbackDao;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.FeedbackEntity;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.out.FeedbackDBMapper;
import com.entrevistador.generadorfeedback.infrastructure.repositoy.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FeedbackDBDao implements FeedbackDao {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackDBMapper feedbackDBMapper;

    @Override
    public Mono<Feedback> obtenerFeedback(String entrevistaId) {
        return this.feedbackRepository.findByIdEntrevista(entrevistaId)
                .map(this.feedbackDBMapper::mapOutFeedbackEntityToFeedback);
    }

    @Override
    public Mono<Feedback> guardarPreguntas(Feedback feedback) {
        return Mono.just(this.feedbackDBMapper.mapInFeedbackToFeedbackEntity(feedback))
                .flatMap(this.feedbackRepository::save)
                .map(this.feedbackDBMapper::mapOutFeedbackEntityToFeedback);
    }

    @Override
    public Mono<Feedback> actualizarRespuestas(Feedback feedback) {
        return this.feedbackRepository.findByIdEntrevista(feedback.getIdEntrevista())
                .switchIfEmpty(Mono.error(new FeedbackException("Id de estado no encontrado. ID: " + feedback.getIdEntrevista())))
                .flatMap(feedbackEntity -> {
                    feedback.getProcesoEntrevista().forEach(respuestaComentario -> feedbackEntity.getEntrevista()
                            .stream()
                            .filter(efb -> efb.getIdPregunta().equals(respuestaComentario.getIdPregunta()))
                            .findFirst()
                            .ifPresent(efb -> efb.setRespuesta(respuestaComentario.getRespuesta())));
                    feedbackEntity.setFeedbackProcess(TipoNotificacionEnum.GF);
                    return this.feedbackRepository.save(feedbackEntity);
                })
                .map(this.feedbackDBMapper::mapOutFeedbackEntityToFeedback);
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
                .map(this.feedbackDBMapper::mapOutFeedbackEntityToFeedback);
    }

    @Override
    public Flux<EntrevistaFeedback> obtenerPreguntas(String entrevistaId) {
        return this.feedbackRepository.findByIdEntrevista(entrevistaId)
                .switchIfEmpty(Mono.error(new FeedbackException("Id de estado no encontrado. ID: " + entrevistaId)))
                .map(FeedbackEntity::getEntrevista)
                .flatMapMany(Flux::fromIterable)
                .map(this.feedbackDBMapper::mapOutEntrevistaFeedbackEntityToEntrevistaFeedback);
    }

    @Override
    public Flux<EntrevistaFeedback> obtenerEntrevistaFeedback(String entrevistaId) {
        return this.feedbackRepository.findByIdEntrevista(entrevistaId)
                .switchIfEmpty(Mono.error(new FeedbackException("Id de estado no encontrado. ID: " + entrevistaId)))
                .map(FeedbackEntity::getEntrevista)
                .flatMapMany(Flux::fromIterable)
                .map(this.feedbackDBMapper::mapOutEntrevistaFeedbackEntityToEntrevistaFeedback);
    }

}
