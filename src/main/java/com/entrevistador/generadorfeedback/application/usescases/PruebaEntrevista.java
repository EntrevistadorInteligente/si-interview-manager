package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.domain.model.FeedbackResponse;
import reactor.core.publisher.Flux;

public interface PruebaEntrevista {

    Flux<FeedbackResponse> getPreguntas(String perfil);

}
