package com.entrevistador.generadorfeedback.domain.port.client;

import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.IdEntrevista;
import reactor.core.publisher.Mono;

public interface OrquestadorClient {
    Mono<IdEntrevista> getIdEntrevista(String perfil);
}
