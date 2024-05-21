package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.application.usescases.PruebaEntrevista;
import com.entrevistador.generadorfeedback.domain.model.dto.PreguntaComentarioDto;
import com.entrevistador.generadorfeedback.domain.port.PruebaEntrevistaDao;
import com.entrevistador.generadorfeedback.domain.port.client.OrquestadorClient;
import com.entrevistador.generadorfeedback.infrastructure.properties.WebFluxProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class PruebaEntrevistaService implements PruebaEntrevista {

    private final PruebaEntrevistaDao pruebaEntrevistaDao;
    private final WebFluxProperties webFluxProperties;
    private final OrquestadorClient orquestadorClient;

    @Override
    public Flux<PreguntaComentarioDto> getPreguntas(String perfil) {
        //TODO: Cambiar el perfil por el id de la entrevista y remover id quemado
        //return pruebaEntrevistaDao.getPreguntas("664ce70cef5af413f995589b", webFluxProperties.getLimitPreguntas());
        return orquestadorClient.getIdEntrevista(perfil).flatMapMany(idEntrevista -> pruebaEntrevistaDao.getPreguntas(idEntrevista.getId(),webFluxProperties.getLimitPreguntas()));
    }

}
