package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevista;
import com.entrevistador.generadorfeedback.domain.port.PruebaEntrevistaDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PruebaEntrevistaService implements com.entrevistador.generadorfeedback.application.usescases.PruebaEntrevista {

    private final PruebaEntrevistaDao pruebaEntrevistaDao;

    @Override
    public Flux<PruebaEntrevista> getPreguntas(String rol) {
        return pruebaEntrevistaDao.getPreguntas(rol);
    }

    @Override
    public Mono<PruebaEntrevista> guardarEntrevista(PruebaEntrevista pruebaEntrevista) {
        return pruebaEntrevistaDao.guardarEntrevista(pruebaEntrevista);
    }
}
