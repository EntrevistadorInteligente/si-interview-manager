package com.entrevistador.generadorfeedback.domain.jms;

import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import reactor.core.publisher.Mono;

public interface JmsPublisherClient {
    Mono<Void> enviarsolicitudFeedback(FeedbackDto feedbackDto);


}
