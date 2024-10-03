package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.domain.model.EntrevistaFeedback;
import com.entrevistador.generadorfeedback.domain.model.Feedback;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PreguntaCreation {

    Mono<Void> guardarPreguntas(Feedback feedback);

    Flux<EntrevistaFeedback> obtenerPreguntas(String entrevitaId);

}
