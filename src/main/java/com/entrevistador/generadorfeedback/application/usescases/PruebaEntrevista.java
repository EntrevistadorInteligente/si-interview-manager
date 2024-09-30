package com.entrevistador.generadorfeedback.application.usescases;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PruebaEntrevista {

    Flux<com.entrevistador.generadorfeedback.domain.model.PruebaEntrevista> getPreguntas(String rol);
    Mono<com.entrevistador.generadorfeedback.domain.model.PruebaEntrevista> guardarEntrevista(com.entrevistador.generadorfeedback.domain.model.PruebaEntrevista pruebaEntrevista);

}
