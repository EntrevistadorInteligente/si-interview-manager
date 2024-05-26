package com.entrevistador.generadorfeedback.domain.port.client;

import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaPrevioFeedbackDto;
import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaPruebaDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackComentarioDto;
import reactor.core.publisher.Flux;

public interface PreparadorEntrevistaClient {
    Flux<FeedbackComentarioDto> obtenerFeedbackMuestra(EntrevistaPruebaDto entrevista);
}
