package com.entrevistador.generadorfeedback.domain.port;

import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevista;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PruebaEntrevistaDao {
    Flux<PruebaEntrevista> getPreguntas(String rol);
    Mono<PruebaEntrevista> guardarEntrevista(PruebaEntrevista request);
}
