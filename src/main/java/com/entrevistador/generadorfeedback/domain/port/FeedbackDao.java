package com.entrevistador.generadorfeedback.domain.port;

import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import reactor.core.publisher.Mono;

public interface FeedbackDao {

    Mono<FeedbackDto> createFeedback();

}
