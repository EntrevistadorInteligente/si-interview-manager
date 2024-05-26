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
public class EntrevistaPrevioFeedbackDto {
    private String perfil;
    @JsonProperty("proceso_entrevista")
    private List<RespuestasPruebaDto> procesoEntrevista;
}
