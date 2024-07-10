package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.domain.model.FeedbackResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeedbackCreation {
    Mono<Void> actualizarFeedback(Feedback feedbackDto);

    Flux<FeedbackResponse> obtenerFeedback(String idEntrevista);
}
