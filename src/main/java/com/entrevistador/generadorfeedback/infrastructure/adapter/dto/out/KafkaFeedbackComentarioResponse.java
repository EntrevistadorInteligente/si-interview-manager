package com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KafkaFeedbackComentarioResponse {
    @JsonProperty("id_pregunta")
    private String idPregunta;
    private String feedback;
    private String score;
}