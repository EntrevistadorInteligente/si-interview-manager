package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.domain.model.dto.PreguntaComentarioDto;
import reactor.core.publisher.Flux;

public interface PruebaEntrevista {

    Flux<PreguntaComentarioDto> getPreguntas(String perfil);

}
