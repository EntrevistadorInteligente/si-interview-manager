package com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePreguntaRequest {
    private String descripcion;
    private String titulo;
}