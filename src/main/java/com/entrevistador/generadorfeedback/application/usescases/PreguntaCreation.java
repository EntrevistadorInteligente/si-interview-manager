package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.domain.model.Entrevista;
import com.entrevistador.generadorfeedback.domain.model.PreguntaComentarioEntrevista;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PreguntaCreation {

    Mono<Void> guardarPreguntas(Entrevista entrevistaDto);

    Flux<PreguntaComentarioEntrevista> obtenerPreguntas(String entrevitaId);

}
