package com.entrevistador.generadorfeedback.domain.port;

import com.entrevistador.generadorfeedback.domain.model.FeedbackResponse;
import reactor.core.publisher.Flux;

public interface PruebaEntrevistaDao {
    Flux<FeedbackResponse> getPreguntas(String idEntrevista, int limit);
}
