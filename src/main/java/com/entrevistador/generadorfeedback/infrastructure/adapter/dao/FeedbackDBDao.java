package com.entrevistador.generadorfeedback.infrastructure.adapter.dao;

import com.entrevistador.generadorfeedback.domain.excepciones.FeedbackException;
import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaFeedbackDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import com.entrevistador.generadorfeedback.domain.port.FeedbackDao;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.Feedback;
import com.entrevistador.generadorfeedback.infrastructure.repositoy.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FeedbackDBDao implements FeedbackDao {
    private final FeedbackRepository feedbackRepository;

    @Override
    public Mono<FeedbackDto> createFeedback(String idEntrevista, List<String> preguntas) {
        return this.feedbackRepository.save(Feedback.builder()
                        .idEntrevista(idEntrevista)
                        .feedback(preguntas.stream().map(s -> EntrevistaFeedbackDto.builder()
                                .pregunta(s)
                                .build()).toList())
                        .build())
                .map(feedback -> FeedbackDto.builder()
                        .idEntrevista(idEntrevista)
                        .procesoEntrevista(feedback.getFeedback())
                        .build());
    }

    @Override
    public Mono<FeedbackDto> actualizarProcesoFeedback(FeedbackDto preguntasDto) {

        return this.feedbackRepository.findByIdEntrevista(preguntasDto.getIdEntrevista())

                .switchIfEmpty(Mono.error(new FeedbackException("Id de estado no encontrado. ID: " + preguntasDto.getIdEntrevista())))
                .flatMap(feedback ->  this.feedbackRepository.save(Feedback.builder()
                                .uuid(feedback.getUuid())
                                .idEntrevista(feedback.getIdEntrevista())
                                .feedback(preguntasDto.getProcesoEntrevista())
                        .build()))
                .map(feedback -> FeedbackDto.builder()
                        .idEntrevista(feedback.getIdEntrevista())
                        .procesoEntrevista(feedback.getFeedback())
                        .build());
    }

}
