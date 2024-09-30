package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.domain.model.Feedback;
import reactor.core.publisher.Mono;

public interface RespuestaCreation {

    Mono<Void> iniciarSolicitudFeedback(Feedback feedback);
}
