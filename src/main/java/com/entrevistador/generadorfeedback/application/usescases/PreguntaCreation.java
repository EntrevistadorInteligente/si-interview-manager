package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaDto;
import com.entrevistador.generadorfeedback.domain.model.dto.PreguntaComentarioDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

    public interface PreguntaCreation {

    Mono<Void> guardarPreguntas(EntrevistaDto entrevistaDto);
    Flux<PreguntaComentarioDto> obtenerPreguntas(String entrevitaId);

}
