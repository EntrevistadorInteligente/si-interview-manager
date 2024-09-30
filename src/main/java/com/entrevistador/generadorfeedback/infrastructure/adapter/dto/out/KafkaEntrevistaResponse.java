package com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KafkaEntrevistaResponse {
    @JsonProperty("id_entrevista")
    private String idEntrevista;
    @JsonProperty("username")
    private String username;
    private List<KafkaPreguntaGeneradaResponse> preguntas;
}
