package com.entrevistador.generadorfeedback.domain.port;

import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackResponseDto;
import reactor.core.publisher.Flux;

public interface PruebaEntrevistaDao {
    Flux<FeedbackResponseDto> getPreguntas(String idEntrevista, int limit);

}
