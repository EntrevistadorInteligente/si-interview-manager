package com.entrevistador.generadorfeedback.domain.model.dto;

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
public class EntrevistaDto {
    @JsonProperty("id_entrevista")
    private String idEntrevista;
    private List<String> preguntas;
}
