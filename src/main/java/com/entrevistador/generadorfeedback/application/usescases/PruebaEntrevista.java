package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackResponseDto;
import reactor.core.publisher.Flux;

public interface PruebaEntrevista {

    Flux<FeedbackResponseDto> getPreguntas(String perfil);

}
