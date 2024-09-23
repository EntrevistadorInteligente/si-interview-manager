package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevistaRequest;
import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevistaResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PruebaEntrevista {

    Flux<PruebaEntrevistaResponse> getPreguntas(String rol);
    Mono<PruebaEntrevistaResponse> guardarEntrevista(PruebaEntrevistaRequest request);

}
