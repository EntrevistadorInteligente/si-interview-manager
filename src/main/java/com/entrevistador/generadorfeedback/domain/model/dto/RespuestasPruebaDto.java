package com.entrevistador.generadorfeedback.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespuestasPruebaDto {
    @JsonProperty("id_pregunta")
    private String idPregunta;
    private String pregunta;
    private String respuesta;
}
