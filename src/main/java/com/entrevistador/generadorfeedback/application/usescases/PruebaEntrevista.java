package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.domain.model.dto.SoloPreguntaImp;
import reactor.core.publisher.Flux;

public interface PruebaEntrevista {

    Flux<SoloPreguntaImp> getPreguntas(String perfil);

}
