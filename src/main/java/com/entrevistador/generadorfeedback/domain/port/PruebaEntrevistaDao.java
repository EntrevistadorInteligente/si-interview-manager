package com.entrevistador.generadorfeedback.domain.port;

import com.entrevistador.generadorfeedback.domain.model.dto.SoloPreguntaImp;
import reactor.core.publisher.Flux;

public interface PruebaEntrevistaDao {
    Flux<SoloPreguntaImp> getPreguntas(String idEntrevista, int limit);
}