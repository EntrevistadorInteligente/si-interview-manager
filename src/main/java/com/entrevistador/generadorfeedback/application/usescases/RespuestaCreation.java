package com.entrevistador.generadorfeedback.application.usescases;

import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import com.entrevistador.generadorfeedback.domain.model.dto.RespuestaDto;
import reactor.core.publisher.Mono;

public interface RespuestaCreation {

    Mono<Void> iniciarSolicitudFeedback(RespuestaDto respuestaDto);
}
