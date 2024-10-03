package com.entrevistador.generadorfeedback.domain.port;

import com.entrevistador.generadorfeedback.domain.model.EntrevistaFeedback;
import com.entrevistador.generadorfeedback.domain.model.Feedback;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeedbackDao {
    Mono<Feedback> obtenerFeedback(String entrevistaId);

    Mono<Feedback> guardarPreguntas(Feedback feedback);

    Mono<Feedback> actualizarRespuestas(Feedback feedback);

    Mono<Feedback> actualizarFeedback(Feedback feedback);

    Flux<EntrevistaFeedback> obtenerPreguntas(String entrevistaId);

    Flux<EntrevistaFeedback> obtenerEntrevistaFeedback(String entrevistaId);
}
