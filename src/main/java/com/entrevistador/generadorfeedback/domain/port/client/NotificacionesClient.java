package com.entrevistador.generadorfeedback.domain.port.client;

import com.entrevistador.generadorfeedback.domain.model.Notificacion;
import reactor.core.publisher.Mono;

public interface NotificacionesClient {
    Mono<Void> enviar(String userId, Notificacion notificacion);
}
