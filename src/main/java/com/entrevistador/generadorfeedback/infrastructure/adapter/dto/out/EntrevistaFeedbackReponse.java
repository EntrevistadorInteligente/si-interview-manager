package com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EntrevistaFeedbackReponse {
    private String idPregunta;
    private String pregunta;
    private String respuesta;
    private String feedback;
}