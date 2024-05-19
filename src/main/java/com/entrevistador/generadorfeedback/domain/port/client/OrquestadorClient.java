package com.entrevistador.generadorfeedback.domain.port.client;

import com.entrevistador.generadorfeedback.domain.model.dto.IdEntrevista;
import com.entrevistador.generadorfeedback.domain.model.enums.TipoNotificacionEnum;
import reactor.core.publisher.Mono;

public interface OrquestadorClient {
    Mono<IdEntrevista> getIdEntrevista(String perfil);
}
