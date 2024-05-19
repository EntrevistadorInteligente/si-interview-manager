package com.entrevistador.generadorfeedback.domain.jms;

import com.entrevistador.generadorfeedback.domain.model.dto.RespuestaDto;
import reactor.core.publisher.Mono;

public interface JmsPublisherClient {
    Mono<Void> enviarsolicitudFeedback(RespuestaDto respuestaDto);
}
