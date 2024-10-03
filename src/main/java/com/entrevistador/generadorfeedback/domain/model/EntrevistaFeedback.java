package com.entrevistador.generadorfeedback.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EntrevistaFeedback {
    private String idPregunta;
    private String pregunta;
    private String respuesta;
    private String feedback;
    private String score;
}