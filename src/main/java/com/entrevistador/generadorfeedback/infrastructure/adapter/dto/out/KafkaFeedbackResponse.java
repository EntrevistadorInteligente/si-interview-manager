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
public class KafkaFeedbackResponse {
    @JsonProperty("id_entrevista")
    private String idEntrevista;
    private String username;
    @JsonProperty("proceso_entrevista")
    private List<KafkaFeedbackComentarioResponse> procesoEntrevista;
}
