package com.entrevistador.generadorfeedback.domain.port;

import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevistaRequest;
import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevistaResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PruebaEntrevistaDao {
    Flux<PruebaEntrevistaResponse> getPreguntas(String rol);
    Mono<PruebaEntrevistaResponse> guardarEntrevista(PruebaEntrevistaRequest request);
}
