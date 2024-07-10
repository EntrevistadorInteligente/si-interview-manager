package com.entrevistador.generadorfeedback.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackComentario {
    private String idPregunta;
    private String feedback;
    private String score;
}