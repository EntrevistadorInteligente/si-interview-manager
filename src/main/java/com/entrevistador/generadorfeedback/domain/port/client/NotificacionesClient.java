package com.entrevistador.generadorfeedback.domain.port.client;

import com.entrevistador.generadorfeedback.domain.model.dto.NotifiacionDto;
import reactor.core.publisher.Mono;

public interface NotificacionesClient {
      Mono<Void> enviar(String userId, NotifiacionDto notifiacionDto);
}
