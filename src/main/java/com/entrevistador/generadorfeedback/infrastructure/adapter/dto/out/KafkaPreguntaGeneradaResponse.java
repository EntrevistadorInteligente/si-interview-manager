package com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KafkaPreguntaGeneradaResponse {
    @JsonProperty("question")
    private String pregunta;
}
