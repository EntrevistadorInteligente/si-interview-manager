package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.application.usescases.PruebaEntrevista;
import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevistaRequest;
import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevistaResponse;
import com.entrevistador.generadorfeedback.domain.port.PruebaEntrevistaDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PruebaEntrevistaService implements PruebaEntrevista {

    private final PruebaEntrevistaDao pruebaEntrevistaDao;

    @Override
    public Flux<PruebaEntrevistaResponse> getPreguntas(String rol) {
        return pruebaEntrevistaDao.getPreguntas(rol);
    }

    @Override
    public Mono<PruebaEntrevistaResponse> guardarEntrevista(PruebaEntrevistaRequest request) {
        return pruebaEntrevistaDao.guardarEntrevista(request);
    }
}
