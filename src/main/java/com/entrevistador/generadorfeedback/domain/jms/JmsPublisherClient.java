package com.entrevistador.generadorfeedback.domain.jms;

import com.entrevistador.generadorfeedback.domain.model.Respuesta;
import reactor.core.publisher.Mono;

public interface JmsPublisherClient {
    Mono<Void> enviarsolicitudFeedback(Respuesta respuestaDto);
}
