package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.FiltroEntrevistasResponseDto;
import reactor.core.publisher.Flux;

public interface FiltroEntrevista {
    Flux<FiltroEntrevistasResponseDto> filtrarEntrevistas(String type, String seniority);
}
