package com.entrevistador.generadorfeedback.domain.port;

import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface FeedbackDao {

    Mono<FeedbackDto> createFeedback(String idEntrevista, List<String> preguntas);
    Mono<FeedbackDto> actualizarProcesoFeedback(FeedbackDto preguntasDto);
}
