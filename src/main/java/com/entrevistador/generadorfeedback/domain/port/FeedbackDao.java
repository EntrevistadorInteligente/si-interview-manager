package com.entrevistador.generadorfeedback.domain.port;

import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackResponseDto;
import com.entrevistador.generadorfeedback.domain.model.dto.PreguntaComentarioDto;
import com.entrevistador.generadorfeedback.domain.model.dto.PreguntaDto;
import com.entrevistador.generadorfeedback.domain.model.dto.RespuestaDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeedbackDao {

    Mono<PreguntaDto> guardarPreguntas(EntrevistaDto entrevistaDto);
    Mono<RespuestaDto> actualizarRespuestas(RespuestaDto respuestaDto);
    Mono<FeedbackDto> actualizarFeedback(FeedbackDto feedbackComentarioDto);
    Flux<PreguntaComentarioDto> obtenerPreguntas(String entrevistaId);
    Flux<FeedbackResponseDto> obtenerFeedback(String entrevistaId);
}
