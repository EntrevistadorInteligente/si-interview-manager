package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.domain.model.EntrevistaFeedback;
import com.entrevistador.generadorfeedback.domain.model.Feedback;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeedbackCreation {
    Mono<Void> actualizarFeedback(Feedback feedbackDto);

    Flux<EntrevistaFeedback> obtenerFeedback(String idEntrevista);
}
