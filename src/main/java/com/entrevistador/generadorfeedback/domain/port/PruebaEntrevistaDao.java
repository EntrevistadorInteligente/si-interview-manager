package com.entrevistador.generadorfeedback.domain.port;

import com.entrevistador.generadorfeedback.domain.model.dto.PreguntaComentarioDto;
import reactor.core.publisher.Flux;

public interface PruebaEntrevistaDao {
    Flux<PreguntaComentarioDto> getPreguntas(String idEntrevista, int limit);
}
