package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import reactor.core.publisher.Mono;

public interface FeedbackCreation {

    Mono<FeedbackDto> some(FeedbackDto feedbackDto);

}
