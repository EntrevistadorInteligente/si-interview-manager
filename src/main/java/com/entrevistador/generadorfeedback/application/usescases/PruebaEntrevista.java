package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaPruebaDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackResponseDto;
import com.entrevistador.generadorfeedback.domain.model.dto.PreguntaComentarioDto;
import reactor.core.publisher.Flux;

public interface PruebaEntrevista {

    Flux<FeedbackResponseDto> getPreguntas(String perfil);

    Flux<FeedbackResponseDto> getEntrevistaFeedback(EntrevistaPruebaDto entrevistaPruebaDto);

}
