package com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KafkaPythonRespuestaComentarioRequest {

    @JsonProperty("id_pregunta")
    private String idPregunta;

    @JsonProperty("respuesta")
    private String respuesta;


}