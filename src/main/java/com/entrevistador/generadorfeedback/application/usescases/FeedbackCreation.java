package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeedbackCreation {

    Mono<Void> actualizarFeedback(FeedbackDto feedbackDto);

    Flux<FeedbackResponseDto> obtenerFeedback(String idEntrevista);
}
