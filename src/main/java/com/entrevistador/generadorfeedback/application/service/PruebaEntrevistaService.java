package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.application.usescases.PruebaEntrevista;
import com.entrevistador.generadorfeedback.domain.model.dto.SoloPreguntaImp;
import com.entrevistador.generadorfeedback.domain.port.PruebaEntrevistaDao;
import com.entrevistador.generadorfeedback.infrastructure.properties.WebFluxProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class PruebaEntrevistaService implements PruebaEntrevista {

    private final PruebaEntrevistaDao pruebaEntrevistaDao;
    private final WebFluxProperties webFluxProperties;

    @Override
    public Flux<SoloPreguntaImp> getPreguntas(String perfil) {
        //TODO: Cambiar el perfil por el id de la entrevista y remover id quemado
        return pruebaEntrevistaDao.getPreguntas("662c22602477f7563c14a5c8", webFluxProperties.getLimitPreguntas());
    }

}
