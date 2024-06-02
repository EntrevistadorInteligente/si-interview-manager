package com.entrevistador.generadorfeedback.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponseDto {
    private String idPregunta;
    private String pregunta;
    private String respuesta;
    private String feedback;
}