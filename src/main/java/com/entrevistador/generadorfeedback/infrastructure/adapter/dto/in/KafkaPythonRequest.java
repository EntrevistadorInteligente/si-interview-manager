package com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KafkaPythonRequest {

    @JsonProperty("id_entrevista")
    private String idEntrevista;

    @JsonProperty("proceso_entrevista")
    private List<KafkaPythonRespuestaComentarioRequest> procesoEntrevista;

}