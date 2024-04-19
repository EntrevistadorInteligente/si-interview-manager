package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface FeedbackCreation {

    Mono<FeedbackDto> some(FeedbackDto feedbackDto);
    Mono<Void> crearEspacioEntrevista(EntrevistaDto feedbackDto);
    Mono<Void> iniciarSolicitudFeedback(FeedbackDto feedbackDto);
    Mono<Void> guardarFeedback(FeedbackDto feedbackDto);
}
