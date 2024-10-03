package com.entrevistador.generadorfeedback.domain.port.jms;

import com.entrevistador.generadorfeedback.domain.model.Feedback;
import reactor.core.publisher.Mono;

public interface JmsPublisherClient {
    Mono<Void> enviarsolicitudFeedback(Feedback feedback);
}
