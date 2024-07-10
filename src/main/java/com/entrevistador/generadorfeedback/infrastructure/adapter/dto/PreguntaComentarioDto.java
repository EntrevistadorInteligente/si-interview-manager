package com.entrevistador.generadorfeedback.infrastructure.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaComentarioDto {
    private String idPregunta;
    private String pregunta;
}