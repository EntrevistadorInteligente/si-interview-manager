package com.entrevistador.generadorfeedback.infrastructure.adapter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class EntrevistaFeedbackEntity {
    private String idPregunta;
    private String pregunta;
    private String respuesta;
    private String feedback;
    private String score;

    public void actualzarFeedback(String feedback, String score) {
        this.feedback = feedback;
        this.score = score;
    }
}
