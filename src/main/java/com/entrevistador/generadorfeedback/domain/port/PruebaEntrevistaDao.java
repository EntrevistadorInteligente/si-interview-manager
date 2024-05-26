package com.entrevistador.generadorfeedback.domain.port;

import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackResponseDto;
import com.entrevistador.generadorfeedback.domain.model.dto.PreguntaComentarioDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PruebaEntrevistaDao {
    Flux<FeedbackResponseDto> getPreguntas(String idEntrevista, int limit);

    void guardarPreguntasFeedback(List<FeedbackResponseDto> feedbackResponseDto, String perfil);
}
