package com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRespuestaComentarioRequest {
    private String idPregunta;
    private String respuesta;
}