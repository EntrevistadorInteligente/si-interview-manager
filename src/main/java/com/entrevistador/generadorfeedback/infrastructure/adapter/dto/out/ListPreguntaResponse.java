package com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListPreguntaResponse {
    private String descripcion;
    private String titulo;
}