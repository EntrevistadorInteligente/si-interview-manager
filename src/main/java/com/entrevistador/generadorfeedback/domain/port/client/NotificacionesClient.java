package com.entrevistador.generadorfeedback.domain.port.client;

import com.entrevistador.generadorfeedback.domain.model.enums.TipoNotificacionEnum;
import reactor.core.publisher.Mono;

public interface NotificacionesClient {
    Mono<Void> generarNotificacion(String userId,TipoNotificacionEnum notificacion, Object object);
}
